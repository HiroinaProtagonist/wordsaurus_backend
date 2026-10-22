package com.hiroinaprotagonist.wordsaurus.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.model.output.CombinedWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.service.RhymeBrainService;
import com.hiroinaprotagonist.wordsaurus.rest.service.WordService;
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
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/wordsaurus")
@RequiredArgsConstructor
@Slf4j
public class CombinedController {
    private final WordService wordService;
    private final RhymeBrainService rhymeBrainService;
    private final ExecutorService executorService;

//    ObjectMapper objectMapper = JsonUtil.getObjectMapper();

    @GetMapping("/combi/{word}")
    public ResponseEntity<CombinedWordResponse> getCombinedResponse(
            @PathVariable String word) throws ExecutionException, InterruptedException {
        CombinedWordResponse output = new CombinedWordResponse();

//        log.info("Get combined results for {}", word);

        Callable<List<RBOutputRhymeRes>> rhymeCall = () ->
                rhymeBrainService.getRhymes(word);

        Callable<List<MWOutputThesRes>> wordCall = () ->
                wordService.getWordFromThesaurus(word);

        try {
            Future<List<RBOutputRhymeRes>> rhymes = getFuture(rhymeCall);
            Future<List<MWOutputThesRes>> words = getFuture(wordCall);
            output.setSearch(word);



            output.setRhymes(rhymes.get());
            output.setWords(words.get());

        } catch (ExecutionException | InterruptedException e) {
            log.error("Error: {}; Error class: {}",
                    e.getMessage(),
                    e.getClass().getSimpleName());
//            throw e;
        }

        log.info("Combined results: {}", output);
        return ResponseEntity.ok(output);
    }

    public <T> Future<T> getFuture(Callable<T> callable) {
        return executorService.submit(callable);
    }
}
