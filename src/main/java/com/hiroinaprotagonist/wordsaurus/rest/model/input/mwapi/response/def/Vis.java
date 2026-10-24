package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vis(
        MWVisT[] t
) {}
