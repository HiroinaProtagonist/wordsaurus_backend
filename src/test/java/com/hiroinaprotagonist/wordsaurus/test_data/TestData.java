package com.hiroinaprotagonist.wordsaurus.test_data;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.Meta;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.mwapi.response.meta.MWTarget;

public class TestData {

    public static final String SHORT_DEFINITION_STRING = "short definition string";
    public static final String VERB_TENSE_STRING_OF_D_LINK_PANT_PANT = "Verb tense string of {d_link|pant|pant}";
    public static final String PART_OF_SPEECH_STRING = "Part of speech string";
    public static final String SEARCH_WORD_OR_RELATED_STRING = "search word or related string";
    public static final String FIRST_ANTONYM = "first antonym";
    public static final String SECOND_ANTONYM = "second antonym";
    public static final String THIRD_ANTONYM = "third antonym";
    public static final String FIRST_SYNONYM = "first synonym";
    public static final String SECOND_SYNONYM = "second synonym";
    public static final String THIRD_SYNONYM = "third synonym";
    public static final String SECOND_STRING_OF_RELATED_WORDS = "second string of related words";
    public static final String FIRST_STRING_OF_RELATED_WORDS = "first string of related words";
    public static final String SECTION_STRING = "section string";
    public static final String SRC_STRING = "src string";
    public static final String UUID_STRING = "uuid string";
    public static final String ID_STRING = "id string";
    public static final String UUID_OF_RESULT_WORD_SOURCE_STRING = "uuid of result word source string";
    public static final String DICTIONARY_THAT_WAS_SEARCHED_STRING = "dictionary that was searched string";

    public static MWTarget getMWTarget() {
        return MWTarget.builder()
                .tuuid(UUID_OF_RESULT_WORD_SOURCE_STRING)
                .tsrc(DICTIONARY_THAT_WAS_SEARCHED_STRING)
                .build();
    }

    public static Meta getMWMeta() {
        return Meta.builder()
                .id(ID_STRING)
                .uuid(UUID_STRING)
                .src(SRC_STRING)
                .section(SECTION_STRING)
                .stems(new String[]{FIRST_STRING_OF_RELATED_WORDS,
                        SECOND_STRING_OF_RELATED_WORDS})
                .syns(new String[][]{{FIRST_SYNONYM, SECOND_SYNONYM, THIRD_SYNONYM}})
                .ants(new String[][]{{FIRST_ANTONYM, SECOND_ANTONYM, THIRD_ANTONYM}})
                .target(getMWTarget())
                .build();
    }



    public static Hwi getHwi() {
        return Hwi.builder()
                .hw(SEARCH_WORD_OR_RELATED_STRING)
                .build();
    }

    public static MWThesaurusWord getInputMWThesaurusWord() {
        return MWThesaurusWord
                .builder()
                .meta(getMWMeta())
                .hwi(getHwi())
                .fl(PART_OF_SPEECH_STRING)
                .sls(new String[]{VERB_TENSE_STRING_OF_D_LINK_PANT_PANT})
                .shortDef(new String[]{SHORT_DEFINITION_STRING})
                .build();
    }
}
