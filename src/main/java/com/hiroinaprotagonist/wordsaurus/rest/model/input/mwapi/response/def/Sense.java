package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record Sense(
        String sn,
        String sgram,
        Dt[][] dt,
        Vis[] vis,
        Sdsense sdsense,
        Wd[][] syn_list,
        Wd[][] rel_list,
        Wd[][] near_list,
        Wd[][] ant_list
) {}
