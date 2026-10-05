package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.def.Sseq;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Def(
        String vd,
//        Sls sls,
        Sseq[] sseq
) {}
