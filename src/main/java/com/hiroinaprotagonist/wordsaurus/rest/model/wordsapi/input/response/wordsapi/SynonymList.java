package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.wordsapi;

public record SynonymList(
        String word,
        String[] synonyms
) {}
