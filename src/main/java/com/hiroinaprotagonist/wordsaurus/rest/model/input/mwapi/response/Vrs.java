package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.hwi.Prs;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vrs(
        String vl,
        String va,
        Prs prs,
        Spl spl

) {}
