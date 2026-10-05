package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.meta.AntArray;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.meta.MWTarget;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.meta.SynArray;

public record Meta(
        String id,
        String uuid,
        String sort,
        String src,
        String section,
        String[] stems,
        String[][] syns,
        String[][] ants,
        MWTarget target,
        boolean offensive
) {}
