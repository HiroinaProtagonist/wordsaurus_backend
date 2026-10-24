package com.hiroinaprotagonist.wordsaurus.rest.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWShortOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWShortOutputThesResWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBShortOutputRhymeRes;
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
public class CombinedShortWordResponse {
    private String search;
    private List<MWShortOutputThesRes> words;
    private List<RBShortOutputRhymeRes> rhymes;
}
