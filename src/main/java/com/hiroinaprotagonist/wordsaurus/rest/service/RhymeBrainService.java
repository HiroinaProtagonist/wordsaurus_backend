package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.converter.RBtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.hiroinaprotagonist.wordsaurus.utility.ErrorUtil.handleErrorWithLog;

@Service
@Slf4j
public class RhymeBrainService {
    @Value("${rb.urls.base.scheme}")
    private String rbScheme;

    @Value("${rb.urls.base.host}")
    private String rhymeBrainHost;

    @Value("${rb.urls.base.path}")
    private String rhymeBrainPath;

    @Value("${rb.urls.rhyming.function}")
    private String rbRhymingFx;

    @Value("${rb.urls.word_info.function}")
    private String rbWordInfoFx;

    @Value("${rb.urls.portmanteau.function}")
    private String rbPortFx;

    private final RestClient restClient;
    private final ExecutorService executorService;
    private final RhymeBrainService selfService;

    public RhymeBrainService(RestClient restClient,
                             ExecutorService executorService,
                             @Lazy RhymeBrainService selfService) {
        this.restClient = restClient;
        this.executorService = executorService;
        this.selfService = selfService;
    }

    private Future<RBShortOutputRhymeRes> makeGetRhymeThread(String word) {
        Callable<RBShortOutputRhymeRes> rhymeCall = () ->
                selfService.getShortRhyme(word);

        return executorService.submit(rhymeCall);
    }

//    @Cacheable(value = "short_rhymes", key="#word")
    public List<RBShortOutputRhymeRes> getShortRhymes(String... words) {

        //create a thread -> future for each word
        //call getShortRhyme for each thread
        List<Future<RBShortOutputRhymeRes>> output =
                Arrays.stream(words)
                        .map(this::makeGetRhymeThread)
                                .toList();

        //join threads
        List<RBShortOutputRhymeRes> rhymesList = output
                .stream().parallel().map(r -> {
                    try {
                        return r.get();
                    } catch (InterruptedException|ExecutionException e) {
                        handleErrorWithLog(log, e);

                        //TODO check this error handling
                        return ResponseEntity.internalServerError().build();
                    }
                });

        return rhymesList;
    }

    //Gets short rhymes for all words returned from word service
    @Cacheable(value = "short_rhyme", key="#word")
    public RBShortOutputRhymeRes getShortRhyme(@RequestParam String word) {
        List<RBShortOutputRhymeResItem> outputWords = Collections.emptyList();

        ResponseEntity<List<RBInputRhymeResItem>> res = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(rbScheme)
                        .host(rhymeBrainHost)
                        .path(rhymeBrainPath)
                        .queryParam("function", rbRhymingFx)
                        .queryParam("word", word)
                        .build()
                )
                .headers(headers -> {})
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>(){});

        try {
//            log.info("Res: {}", res);

            if (Objects.nonNull(res.getBody())) {
                outputWords = res.getBody()
                        .stream()
                        .map(RBInputRhymeResItem::setFlagVariables)
                        .map(RBtoResMapper.MAPPER_INSTANCE::rbToShortResMapper)
                        .toList();
            }
        } catch(Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        RBShortOutputRhymeRes rhymePayload = RBShortOutputRhymeRes
                .builder()
                .search(word)
                .rhymes(outputWords)
                .build();

        //set map of rhymes by score
        generateAllRhymes(rhymePayload);
        return rhymePayload;
    }

    //TODO make private
    public void processRhymeWordIntoMap(Map<Integer, List<String>> map, RBShortOutputRhymeResItem item) {
        Integer score = item.getScore();
        String word = item.getWord();

        if(map.containsKey(score)) {
            List<String> added = map.get(score);
            added.add(word);

            map.put(score, added);
        } else {
            List<String> list = new LinkedList<>();
            list.add(word);

            map.put(score, list);
        }
    }

    private void generateAllRhymes(RBShortOutputRhymeRes rhymes) {
        Map<Integer, List<String>> map = new HashMap<>();
        rhymes.getRhymes().forEach(r -> processRhymeWordIntoMap(map, r));

        rhymes.setAllRhymes(map);
    }

    @Cacheable(value = "rhymes", key="#word")
    public List<RBOutputRhymeRes> getRhymes(@RequestParam String word) {
        List<RBOutputRhymeResItem> outputWords = Collections.emptyList();

        ResponseEntity<List<RBInputRhymeResItem>> res = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(rbScheme)
                        .host(rhymeBrainHost)
                        .path(rhymeBrainPath)
                        .queryParam("function", rbRhymingFx)
                        .queryParam("word", word)
                        .build()
                )
                .headers(headers -> {})
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>(){});

        try {
//            log.info("Res: {}", res);

            if (Objects.nonNull(res.getBody())) {
                outputWords = res.getBody()
                        .stream()
                        .map(RBInputRhymeResItem::setFlagVariables)
                        .map(RBtoResMapper.MAPPER_INSTANCE::rbToResMapper)
                        .toList();
                }
        } catch(Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        return List.of(RBOutputRhymeRes
                .builder()
                .search(word)
                .rhymes(outputWords)
                .build());
    }

    @Cacheable(value = "word_info", key="#word")
    public List<RBOutputRhymeResItem> getWordInfo(@RequestParam String word) {
        log.info("Unimplemented word info method");
        return Collections.emptyList();
    }

    @Cacheable(value = "portmanteaus", key="#word")
    public List<RBOutputRhymeResItem> getPortmanteaus(@RequestParam String word) {
        log.info("Unimplemented portmanteau method");
        return Collections.emptyList();
    }
}
