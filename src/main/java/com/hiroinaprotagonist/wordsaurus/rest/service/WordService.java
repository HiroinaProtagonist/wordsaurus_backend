package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.converter.MWtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWThesResWord;
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
public class WordService {
    private final RestClient restClient;

    public WordService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Value("${mw.urls.base.collegiate.url}")
    private String collegiateBaseUrl;

    @Value("${mw.urls.base.thesaurus.url}")
    private String thesaurusBaseUrl;

    @Value("${mw.urls.scheme}")
    private String mWScheme;

    @Value("${mw.urls.host}")
    private String mWHost;

    @Value("${mw.urls.base.thesaurus.path}")
    private String thesaurusPath;

    @Value("${mw.urls.base.collegiate.path}")
    private String collegiatePath;

    @Value("${mw.keys.collegiate}")
    private String collegiateKey;

    @Value("${mw.keys.thesaurus}")
    private String thesaurusKey;

    @Cacheable(value = "wordFromCollegiate", key="#word")
    public List<MWThesRes> getWordFromCollegiate(@RequestParam String word) {
        List<MWThesResWord> outputWords = Collections.emptyList();

        ResponseEntity<List<MWThesaurusWord>> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(mWScheme)
                        .host(mWHost)
                        .path(collegiatePath + word)
                        .queryParam("key", collegiateKey)
                        .build()
                )
                .headers(headers -> {})
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>(){});

        try {
            log.info("Res: {}", response);

            if (Objects.nonNull(response.getBody())) {
                outputWords = response.getBody()
                        .stream()
                        .map(MWtoResMapper.MAPPER_INSTANCE::mwToResMapper)
                        .toList();
            }
        } catch (Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        return List.of(MWThesRes
                .builder()
                .search(word)
                .words(outputWords)
                .build());
    }

    @Cacheable(value = "wordFromThesaurus", key="#word")
    public List<MWThesRes> getWordFromThesaurus(@RequestParam String word) {
        List<MWThesResWord> outputWords = Collections.emptyList();

        ResponseEntity<List<MWThesaurusWord>> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(mWScheme)
                        .host(mWHost)
                        .path(thesaurusPath + word)
                        .queryParam("key", thesaurusKey)
                        .build()
                )
                .headers(headers -> {})
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>(){});


        try {
            log.info("Res: {}", response);

            if (Objects.nonNull(response.getBody())) {
                outputWords = response.getBody()
                        .stream()
                        .map(MWtoResMapper.MAPPER_INSTANCE::mwToResMapper)
                        .toList();

            }
        } catch (Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        return List.of(MWThesRes
                .builder()
                .search(word)
                .words(outputWords)
                .build());
    }
}
