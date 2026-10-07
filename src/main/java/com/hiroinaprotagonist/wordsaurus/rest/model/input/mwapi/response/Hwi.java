package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.hwi.Prs;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Hwi(
        String hw,
        Prs[] prs,
        String psl
) {}
