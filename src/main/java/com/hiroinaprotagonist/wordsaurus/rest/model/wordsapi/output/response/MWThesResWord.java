package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.response;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.annotation.Target;

@Data
@AllArgsConstructor
public class MWThesResWord {
    private Meta meta;
    private String meta_id;
    private String meta_uuid;
    private String meta_src;
    private String meta_section;
    private Target target;
    private String part_of_speech;
    private String[] short_def;

    private String[] stems;
    private String[] synonyms;
    private String[] antonyms;
    private String[] rhyming;

    private String offensive;
    private Hwi hwi;
}
