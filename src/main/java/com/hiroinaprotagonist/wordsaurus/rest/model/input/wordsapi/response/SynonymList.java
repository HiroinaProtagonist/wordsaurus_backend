package com.hiroinaprotagonist.wordsaurus.rest.model.input.wordsapi.response;

public record SynonymList(
        String word,
        String[] synonyms
) {}
