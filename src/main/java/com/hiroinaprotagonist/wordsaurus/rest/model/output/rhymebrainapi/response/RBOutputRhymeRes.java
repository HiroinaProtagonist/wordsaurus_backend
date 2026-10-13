package com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RBOutputRhymeRes {
    private String search;
    private List<RBOutputRhymeResItem> rhymes;
}
