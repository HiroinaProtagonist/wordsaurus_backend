package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.converter.RBtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RhymeBrainOutputRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RhymeBrainOutputResItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
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
    public List<RhymeBrainOutputRes> getRhymes(@RequestParam String word) {
        List<RhymeBrainOutputResItem> outputWords = Collections.emptyList();

        ResponseEntity<List<RBRhymeResItem>> res = restClient.get()
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
                        .map(RBRhymeResItem::setFlagVariables)
                        .map(RBtoResMapper.MAPPER_INSTANCE::rbToResMapper)
                        .toList();
                }
        } catch(Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        return List.of(RhymeBrainOutputRes
                .builder()
                .search(word)
                .words(outputWords)
                .build());
    }

    @Cacheable(value = "word_info", key="#word")
    public List<RhymeBrainOutputResItem> getWordInfo(@RequestParam String word) {
        log.info("Unimplemented word info method");
        return Collections.emptyList();
    }

    @Cacheable(value = "portmanteaus", key="#word")
    public List<RhymeBrainOutputResItem> getPortmanteaus(@RequestParam String word) {
        log.info("Unimplemented portmanteau method");
        return Collections.emptyList();
    }
}
