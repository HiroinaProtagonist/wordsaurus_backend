package com.hiroinaprotagonist.wordsaurus.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.model.output.CombinedShortWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.CombinedWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWShortOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWShortOutputThesResWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBShortOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.Rhyme;
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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hiroinaprotagonist.wordsaurus.utility.ErrorUtil.handleErrorWithLog;

@RestController
@RequestMapping("/api/wordsaurus")
@RequiredArgsConstructor
@Slf4j
public class CombinedController {
    private final WordService wordService;
    private final RhymeBrainService rhymeBrainService;
    private final ExecutorService executorService;

    ObjectMapper objectMapper = JsonUtil.getObjectMapper();

    @GetMapping("/combi/short/{words}")
    public ResponseEntity<CombinedShortWordResponse> getCombinedShortResponse(
            @PathVariable String words) throws ExecutionException, InterruptedException {
        CombinedShortWordResponse output = new CombinedShortWordResponse(words, null, null);

        log.info("Get combined results for {}", words);

        Callable<List<MWShortOutputThesRes>> wordCall = () ->
                wordService.getShortWordFromThesaurus(words);

        try {

            Future<List<MWShortOutputThesRes>> futureWords = getFuture(wordCall);
            List<MWShortOutputThesRes> currentWords = futureWords.get();
            output.setWords(currentWords);

            //TODO get all distinct words that were found in words words.meta.id to get lists of rhymes for all of them
            List<String> wordsToRhyme = currentWords.stream()
                    .map(c->c.getWords().stream()
                            .map(w -> w.getMetaId()).toList().toString())
                            .toList();

            Callable<List<RBShortOutputRhymeRes>> rhymeCall = () ->
                    rhymeBrainService.getShortRhymes(wordsToRhyme.toArray(String[]::new));

            Future<List<RBShortOutputRhymeRes>> rhymes = getFuture(rhymeCall);
            output.setRhymes(rhymes.get());


        } catch (ExecutionException | InterruptedException e) {
            handleErrorWithLog(log, e);

            //TODO check this error handling
            return ResponseEntity.internalServerError().build();
        }

        log.info("Combined short results: {}", objectMapper.writeValueAsString(output));
        return ResponseEntity.ok(output);
    }

    @GetMapping("/combi/{word}")
    public ResponseEntity<CombinedWordResponse> getCombinedResponse(
            @PathVariable String word) throws ExecutionException, InterruptedException {
        CombinedWordResponse output = new CombinedWordResponse();
        output.setSearch(word);

        log.info("Get combined results for {}", word);

        Callable<List<RBOutputRhymeRes>> rhymeCall = () ->
                rhymeBrainService.getRhymes(word);

        Callable<List<MWOutputThesRes>> wordCall = () ->
                wordService.getWordFromThesaurus(word);

        try {
            Future<List<RBOutputRhymeRes>> rhymes = getFuture(rhymeCall);
            Future<List<MWOutputThesRes>> words = getFuture(wordCall);

            output.setRhymes(rhymes.get());
            output.setWords(words.get());

        } catch (ExecutionException | InterruptedException e) {
            log.error("Error: {}; Error class: {}",
                    e.getMessage(),
                    e.getClass().getSimpleName());

            //TODO check this error handling
            return ResponseEntity.internalServerError().build();
        }

        log.info("Combined results: {}", objectMapper.writeValueAsString(output));
        return ResponseEntity.ok(output);
    }

    public <T> Future<T> getFuture(Callable<T> callable) {
        return executorService.submit(callable);
    }
}
