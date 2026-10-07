package com.hiroinaprotagonist.wordsaurus.rest.model.input.wordsapi.response;

public record SimilarToList(
        String word,
        String[] similarTo
) {}
