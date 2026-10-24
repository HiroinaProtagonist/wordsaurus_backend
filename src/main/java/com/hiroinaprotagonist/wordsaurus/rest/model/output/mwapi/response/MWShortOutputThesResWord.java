package com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.meta.MWTarget;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MWShortOutputThesResWord {
    private String metaId;
    private String metaUuid;
    private String metaSrc;
    private String metaSection;
//    private MWTarget mwTarget;
    private String partOfSpeech;
    private String[] shortDef;

    private String[] stems;
    private String[][] synonyms;
    private String[][] antonyms;

//    private String[][] syn_list;
//    private String[][] rel_list;
//    private String[][] near_list;
//    private String[][] ant_list;

    private Boolean offensive;
    private Hwi hwi;
    }
