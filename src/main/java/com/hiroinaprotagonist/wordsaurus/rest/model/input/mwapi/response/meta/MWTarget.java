package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.meta;

import lombok.Builder;

@Builder
public record MWTarget(
        String tuuid,
        Object tsrc

) {}
