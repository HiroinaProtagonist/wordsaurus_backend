package com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class RBInputRhymeResItem {
    private String word;
    private Integer freq;
    private Integer score;
    private String flags;
    private String syllables;

    private Boolean isOffensive;
    private Boolean isDictionaryWord;
    private Boolean isKnownPronunciation;

    public boolean flagIsOffensive() {
        if(isNull(flags)) { return false; }
        return flags.contains("a");
    }

    public boolean flagIsDictionaryWord() {
        if(isNull(flags)) { return false; }
        return flags.contains("b");
    }

    public boolean flagIsKnownPronunciation() {
        if(isNull(flags)) { return false; }
        return flags.contains("c");
    }

    public RBInputRhymeResItem setFlagVariables() {
        this.isOffensive = this.flagIsOffensive();
        this.isDictionaryWord = this.flagIsDictionaryWord();
        this.isKnownPronunciation = this.flagIsKnownPronunciation();
        return this;    //for use in map in RhymeBrainController, can't return void
    }
}
