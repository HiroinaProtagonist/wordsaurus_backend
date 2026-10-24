package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MWResult {
    public MWInputThesResWord[] words;
}
