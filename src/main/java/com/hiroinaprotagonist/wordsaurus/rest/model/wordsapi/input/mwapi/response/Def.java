package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.def.Sseq;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Def(
        String vd,
//        Sls sls,
        Sseq[] sseq
) {}
