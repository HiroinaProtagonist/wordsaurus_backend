package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.def;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.Ins;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.Vrs;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.hwi.Prs;

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
