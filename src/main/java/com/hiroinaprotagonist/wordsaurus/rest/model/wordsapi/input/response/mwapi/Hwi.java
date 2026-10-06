package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.hwi.Prs;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Hwi(
        String hw,
        Prs[] prs,
        String psl
) {}
