package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.mwapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MWThesRes {
    List<MWThesResWord> words;
}
