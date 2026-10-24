package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dt(
        String[] text,
//        BNW bnw,
//        Ca ca,
//        Ri ri,
//        Snote snote,
//        Uns uns,
        Vis[] vis
) {}
