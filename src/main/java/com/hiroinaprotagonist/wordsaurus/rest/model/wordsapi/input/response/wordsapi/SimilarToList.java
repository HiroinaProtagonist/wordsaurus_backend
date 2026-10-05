package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.wordsapi;

public record SimilarToList(
        String word,
        String[] similarTo
) {}
