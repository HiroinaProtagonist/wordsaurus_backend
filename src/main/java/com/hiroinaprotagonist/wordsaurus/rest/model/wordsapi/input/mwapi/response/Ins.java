package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.hwi.Prs;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Ins(
        @JsonProperty("if")
        String _if,
        String ifc,
        String il,
        Prs prs,
        Spl spl
) {}
