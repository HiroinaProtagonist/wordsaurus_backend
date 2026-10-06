package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.wordsapi.response;

public record SimilarToList(
        String word,
        String[] similarTo
) {}
