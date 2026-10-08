package com.hiroinaprotagonist.wordsaurus.rest.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWThesRes;
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
    private List<MWThesRes> words;
    private List<RBRhymeRes> rhymes;
    private List<String> antonyms;
    private List<String> synonyms;
    private List<String> similar;
}
