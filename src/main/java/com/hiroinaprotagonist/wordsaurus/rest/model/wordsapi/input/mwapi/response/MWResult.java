package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MWResult {
    public MWThesaurusWord[] words;
}
