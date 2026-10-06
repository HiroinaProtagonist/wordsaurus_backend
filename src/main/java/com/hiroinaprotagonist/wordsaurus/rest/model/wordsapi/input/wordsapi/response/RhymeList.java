package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.wordsapi.response;

public record RhymeList(
        String word,
        RhymeAllList rhymes
) {}
