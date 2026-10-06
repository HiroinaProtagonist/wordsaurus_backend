package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.wordsapi.response;

public record SynonymList(
        String word,
        String[] synonyms
) {}
