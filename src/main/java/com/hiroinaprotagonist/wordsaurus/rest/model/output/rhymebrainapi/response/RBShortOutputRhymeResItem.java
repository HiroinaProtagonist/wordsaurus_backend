package com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RBShortOutputRhymeResItem {
    private String word;
    private Integer freq;
    private Integer score;
    private boolean isOffensive;
    private Integer syllables;
}
