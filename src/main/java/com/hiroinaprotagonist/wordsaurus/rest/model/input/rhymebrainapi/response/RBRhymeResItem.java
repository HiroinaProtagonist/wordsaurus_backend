package com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class RBRhymeResItem {
    private String word;
    private Integer freq;
    private Integer score;
    private String flags;
    private String syllables;

    private Boolean isOffensive;
    private Boolean isDictionaryWord;
    private Boolean isKnownPronunciation;

    public boolean flagIsOffensive() {
        return flags.contains("a");
    }

    public boolean flagIsDictionaryWord() {
        return flags.contains("b");
    }

    public boolean flagHasKnownPronunciation() {
        return flags.contains("c");
    }

    public RBRhymeResItem setFlagVariables() {
        this.isOffensive = flagIsOffensive();
        this.isDictionaryWord = flagIsDictionaryWord();
        this.isKnownPronunciation = flagHasKnownPronunciation();
        return this;    //for use in map in RhymeBrainController, can't return void
    }
}
