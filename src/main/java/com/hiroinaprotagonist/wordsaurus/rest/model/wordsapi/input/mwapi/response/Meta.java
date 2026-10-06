package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.meta.MWTarget;
import lombok.Builder;

@Builder
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
