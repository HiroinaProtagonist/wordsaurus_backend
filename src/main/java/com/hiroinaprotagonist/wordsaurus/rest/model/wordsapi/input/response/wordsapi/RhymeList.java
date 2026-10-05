package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.wordsapi;

public record RhymeList(
        String word,
        RhymeAllList rhymes
) {}
