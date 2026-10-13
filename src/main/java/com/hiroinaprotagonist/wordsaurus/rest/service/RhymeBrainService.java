package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.converter.RBtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeResItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RhymeBrainService {
    private final RestClient restClient;

    public RhymeBrainService(RestClient restClient) {
        this.restClient = restClient;
    }

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
            log.info("Res: {}", res);

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
