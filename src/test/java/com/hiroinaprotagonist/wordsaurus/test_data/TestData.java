package com.hiroinaprotagonist.wordsaurus.test_data;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Meta;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.meta.MWTarget;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeResItem;

public class TestData {

    //MW Constants
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

    //RB Constants
    public static final String WORD = "word string";
    public static final String PRON = "PH ON EM ES string";
    public static final String IPA = "h\\u028cl\\u02c8\\u0259\\u028a\\u032f ipa string";
    public static final Integer HIGH_FREQ = 34;
    public static final Integer LOW_FREQ = 5;
    public static final Integer HIGH_SCORE = 300;
    public static final Integer MID_HIGH_SCORE = 284;
    public static final Integer LOW_SCORE = 20;
    public static final String ALL_FLAGS ="abc";
    public static final String OFFENSIVE_FLAG = "a";
    public static final String DICTIONARY_WORD_FLAG = "b";
    public static final String KNOWN_PRONUNCIATION_FLAG = "c";
    public static final String DICTIONARY_PRONUNCIATION_FLAGS = "bc";

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

    //RB Input
    public static RBInputRhymeResItem getRBRhymeResItem() {
        return RBInputRhymeResItem
                .builder()
                .word(WORD)
                .freq(HIGH_FREQ)
                .score(HIGH_SCORE)
                .flags(ALL_FLAGS)
                .build();
    }

    //RB Output
    public static RBOutputRhymeResItem getRhymeBrainOutputResItem() {
        return RBOutputRhymeResItem
                .builder()
                .word(WORD)
                .freq(HIGH_FREQ)
                .score(HIGH_SCORE)
                .flags(ALL_FLAGS)
                .isOffensive(true)
                .isDictionaryWord(true)
                .isKnownPronunciation(true)
                .build();
    }
}
