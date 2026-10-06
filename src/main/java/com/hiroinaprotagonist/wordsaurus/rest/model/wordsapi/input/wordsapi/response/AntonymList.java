package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.wordsapi.response;

public record AntonymList(
        String word,
        String[] antonyms
) {}
