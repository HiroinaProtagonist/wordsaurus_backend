package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Sense(
        String sn,
        String sgram,
        Dt[][] dt,
        Vis[] vis,
        Sdsense sdsense
) {}
