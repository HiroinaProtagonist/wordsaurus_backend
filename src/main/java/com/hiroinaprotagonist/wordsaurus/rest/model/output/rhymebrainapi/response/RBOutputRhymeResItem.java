package com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RBOutputRhymeResItem {
    private String word;
    private Integer freq;
    private Integer score;
    private String flags;
    private boolean isOffensive;
    private boolean isDictionaryWord;
    private boolean isKnownPronunciation;
    private Integer syllables;
}
