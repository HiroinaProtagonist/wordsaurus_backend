package com.hiroinaprotagonist.wordsaurus.rest.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CombinedWordResponse {
    private String search;
    private List<MWOutputThesRes> words;
    private List<RBOutputRhymeRes> rhymes;
    private List<>
}
