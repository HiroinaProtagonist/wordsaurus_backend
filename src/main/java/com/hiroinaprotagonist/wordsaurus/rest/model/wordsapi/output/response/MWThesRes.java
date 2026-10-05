package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.response;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.annotation.Target;
import java.util.List;

@AllArgsConstructor
@Data
public class MWThesRes {
    List<MWThesResWord> words;
}
