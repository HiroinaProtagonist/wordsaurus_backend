package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.hwi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Prs(
        String mw,
        String l,
        String l2,
        String pun,
        MWSound sound
) {}
