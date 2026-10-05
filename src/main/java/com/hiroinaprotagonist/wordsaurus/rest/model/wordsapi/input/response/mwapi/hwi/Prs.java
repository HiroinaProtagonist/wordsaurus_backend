package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.hwi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Prs(
        String mw,
        String l,
        String l2,
        String pun,
        MWSound sound
) {}
