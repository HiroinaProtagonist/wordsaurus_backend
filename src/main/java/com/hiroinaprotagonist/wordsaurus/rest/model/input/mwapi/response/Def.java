package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.def.Sensb;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.def.Sseq;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record Def(
//        String vd,
//        Sls sls,
//        Sseq[] sseq
        Sensb sensb
) {}
