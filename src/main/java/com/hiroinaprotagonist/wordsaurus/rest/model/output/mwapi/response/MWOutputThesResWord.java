package com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Meta;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.meta.MWTarget;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MWOutputThesResWord {
    private Meta meta;
    private String metaId;
    private String metaUuid;
    private String metaSrc;
    private String metaSection;
    private MWTarget mwTarget;
    private String partOfSpeech;
    private String[] shortDef;

    private String[] stems;
    private String[][] synonyms;
    private String[][] antonyms;
    private String[][] rhyming;

    private String offensive;
    private Hwi hwi;
}
