package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Ins;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Vrs;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.hwi.Prs;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Sdsense(
        String sd,
//        Et et,
        Ins ins,
//        Lbs lbs,
        Prs prs,
        String sgram,
//        Sls sls,
        Vrs vrs,
        Dt dt
) {}
