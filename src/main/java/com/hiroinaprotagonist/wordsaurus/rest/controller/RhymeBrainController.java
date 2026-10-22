package com.hiroinaprotagonist.wordsaurus.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.service.RhymeBrainService;
import com.hiroinaprotagonist.wordsaurus.utility.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/rb")
@RequiredArgsConstructor
@Slf4j
public class RhymeBrainController {
    private final RhymeBrainService rhymeBrainService;

//    ObjectMapper objectMapper = JsonUtil.getObjectMapper();

    @GetMapping("/rhyming/{word}")
    public ResponseEntity<List<RBOutputRhymeRes>> getRhymesFromRB(
            @PathVariable String word) {
        log.info("Get rhymes for {}", word);

        List<RBOutputRhymeRes> rhymes = rhymeBrainService.getRhymes(word);

        return ResponseEntity.ok(rhymes);
    }
}
