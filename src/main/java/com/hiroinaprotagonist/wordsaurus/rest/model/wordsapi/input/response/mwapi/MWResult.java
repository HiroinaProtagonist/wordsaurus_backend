package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MWResult {
    public MWThesaurusWord[] words;
}
