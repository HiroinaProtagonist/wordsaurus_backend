package com.hiroinaprotagonist.wordsaurus.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.CombinedWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.MWResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class WordService {
    @Autowired
    private RestClient restClient;

    @Value("${mw.urls.base.collegiate.url}")
    private String collegiateBaseUrl;

    @Value("${mw.urls.base.thesaurus.url}")
    private String thesaurusBaseUrl;

    @Value("${mw.urls.scheme}")
    private String MWScheme;

    @Value("${mw.urls.host}")
    private String MWHost;

    @Value("${mw.urls.base.thesaurus.path}")
    private String thesaurusPath;

    @Value("${mw.urls.base.collegiate.path}")
    private String collegiatePath;

    @Value("${mw.keys.collegiate}")
    private String collegiateKey;

    @Value("${mw.keys.thesaurus}")
    private String thesaurusKey;

    @Cacheable(value = "wordFromCollegiate", key="#word")
    public MWResult getWordFromCollegiate(@RequestParam String word) {
        ResponseEntity<MWResult> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(MWScheme)
                        .host(MWHost)
                        .path(collegiatePath + word)
                        .queryParam("key", collegiateKey)
                        .build()
                )
                .headers(headers -> {
//                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(MWResult.class);

        log.info("Res: {}", response);
        return response.getBody();
    }

    @Cacheable(value = "wordFromThesaurus", key="#word")
    public String getWordFromThesaurus(@RequestParam String word) {
        ResponseEntity<String> response = restClient.get()
                .uri(MWScheme+"://"+MWHost+"/"+thesaurusPath+word+"?key="+thesaurusKey
                )
                .headers(headers -> {
//                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(String.class);

        log.info("Res: {}", response);
        return response.getBody();
    }
}
