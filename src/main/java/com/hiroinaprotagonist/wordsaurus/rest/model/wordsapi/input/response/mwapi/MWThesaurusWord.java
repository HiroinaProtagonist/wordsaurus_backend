package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MWThesaurusWord(
        Meta meta
//        Integer hom,
//        Hwi hwi,
//        Hwi[] ahws,
//        Vrs[] vrs,
//        String fl,
//        String[] lbs,
//        String[] sls,
//        Ins[] ins,
//        Cxs[] cxs,
//        Def[] def,
//        String[] shortDef
) {}
