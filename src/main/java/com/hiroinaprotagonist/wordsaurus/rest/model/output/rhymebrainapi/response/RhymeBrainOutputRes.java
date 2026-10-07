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
public class RhymeBrainOutputRes {
    private String search;
    private List<RhymeBrainOutputResItem> words;
}
