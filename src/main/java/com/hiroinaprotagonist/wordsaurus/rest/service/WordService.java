package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.converter.MWtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWInputThesResWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesResWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWShortOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWShortOutputThesResWord;
import com.hiroinaprotagonist.wordsaurus.utility.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

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

    ObjectMapper objectMapper = JsonUtil.getObjectMapper();

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
    public List<MWOutputThesRes> getWordFromCollegiate(@RequestParam String word) {
        List<MWOutputThesResWord> outputWords = Collections.emptyList();

        ResponseEntity<List<MWInputThesResWord>> response = restClient.get()
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

        return List.of(MWOutputThesRes
                .builder()
                .search(word)
                .words(outputWords)
                .build());
    }

    @Cacheable(value = "shortWordFromThesaurus", key="#word")
    public List<MWShortOutputThesRes> getShortWordFromThesaurus(@RequestParam String word) {
        List<MWShortOutputThesResWord> outputWords = Collections.emptyList();

        ResponseEntity<List<MWInputThesResWord>> response = restClient.get()
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
//            log.info("Word Res: {}", objectMapper.writeValueAsString(response));

            if (Objects.nonNull(response.getBody())) {
//                log.info("Def print: {}", objectMapper.writeValueAsString(response.getBody().get(0).def()));

                outputWords = response.getBody()
                        .stream()
                        .map(MWtoResMapper.MAPPER_INSTANCE::mwToShortResMapper)
                        .toList();

//                log.info("Word Res after mapping: {}",
//                        objectMapper.writeValueAsString(response));

//                log.info("Def print: {}", (Object) response.getBody().get(0).def());

            }
        } catch (Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        return List.of(MWShortOutputThesRes
                .builder()
                .search(word)
                .words(outputWords)
                .build());
    }

    @Cacheable(value = "wordFromThesaurus", key="#word")
    public List<MWOutputThesRes> getWordFromThesaurus(@RequestParam String word) {
        List<MWOutputThesResWord> outputWords = Collections.emptyList();

        ResponseEntity<List<MWInputThesResWord>> response = restClient.get()
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
            log.info("Word Res: {}", objectMapper.writeValueAsString(response));

            if (Objects.nonNull(response.getBody())) {
//                log.info("Def print: {}", objectMapper.writeValueAsString(response.getBody().get(0).def()));

                outputWords = response.getBody()
                        .stream()
                        .map(MWtoResMapper.MAPPER_INSTANCE::mwToResMapper)
                        .toList();

                log.info("Word Res after mapping: {}",
                        objectMapper.writeValueAsString(response));

//                log.info("Def print: {}", (Object) response.getBody().get(0).def());

            }
        } catch (Exception e) {
            log.error("Error encountered: {}", e.getMessage());
        }

        return List.of(MWOutputThesRes
                .builder()
                .search(word)
                .words(outputWords)
                .build());
    }
}
