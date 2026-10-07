package com.hiroinaprotagonist.wordsaurus.rest.model.input.wordsapi.response;

public record AntonymList(
        String word,
        String[] antonyms
) {}
