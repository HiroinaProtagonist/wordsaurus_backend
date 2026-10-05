package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.wordsapi;

public record AntonymList(
        String word,
        String[] antonyms
) {}
