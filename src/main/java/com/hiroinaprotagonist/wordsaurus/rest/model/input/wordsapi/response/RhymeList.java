package com.hiroinaprotagonist.wordsaurus.rest.model.input.wordsapi.response;

public record RhymeList(
        String word,
        RhymeAllList rhymes
) {}
