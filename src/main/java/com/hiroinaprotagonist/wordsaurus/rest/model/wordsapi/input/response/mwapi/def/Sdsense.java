package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.def;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.Ins;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.Vrs;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.hwi.Prs;

import java.lang.module.ModuleDescriptor;

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
