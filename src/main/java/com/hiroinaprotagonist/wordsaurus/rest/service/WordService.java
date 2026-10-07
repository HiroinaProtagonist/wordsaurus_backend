package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.List;

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
    public List<MWThesaurusWord> getWordFromCollegiate(@RequestParam String word) {
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

        log.info("Res: {}", response);
        return response.getBody();
    }

    @Cacheable(value = "wordFromThesaurus", key="#word")
    public List<MWThesaurusWord> getWordFromThesaurus(@RequestParam String word) {
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

        log.info("Res: {}", response);
        return response.getBody();
    }
}
