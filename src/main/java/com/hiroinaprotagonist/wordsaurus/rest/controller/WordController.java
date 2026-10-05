package com.hiroinaprotagonist.wordsaurus.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.CombinedWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.MWResult;
import com.hiroinaprotagonist.wordsaurus.rest.service.WordService;
import com.hiroinaprotagonist.wordsaurus.utility.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/wordsaurus")
@RequiredArgsConstructor
@Slf4j
public class WordController {
    private final WordService wordService;

//    ObjectMapper objectMapper = JsonUtil.getObjectMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/collegiate/{word}")
    public ResponseEntity<MWResult> getWordDataFromCollegiate(
            @PathVariable String word) {
        log.info("Searching MW collegiate dictionary for {}", word);

        MWResult dictResults = wordService.getWordFromCollegiate(word);

        log.info("Retrieved collegiate dictionary results: {}",
                objectMapper.writeValueAsString(dictResults));
        return ResponseEntity.ok(dictResults);
    }

    @GetMapping("/thesaurus/{word}")
    public ResponseEntity<String> getWordDataFromThesaurus(
            @PathVariable String word) {
        log.info("Searching MW thesaurus for {}", word);

        String thesResults = wordService.getWordFromThesaurus(word);

//        log.info("Retrieved thesaurus results: {}",
//                objectMapper.writeValueAsString(thesResults));
        return ResponseEntity.ok(thesResults);
    }
}
