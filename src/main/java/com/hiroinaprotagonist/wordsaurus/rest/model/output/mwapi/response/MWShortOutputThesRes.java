package com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class MWShortOutputThesRes {
    private String search;
    private List<MWShortOutputThesResWord> words;
}
