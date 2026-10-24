package com.hiroinaprotagonist.wordsaurus.data;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.converter.MWtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Hwi;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWInputThesResWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.Meta;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.meta.MWTarget;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesResWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBShortOutputRhymeResItem;

import java.util.Arrays;
import java.util.List;

public class TestData {

    //TEST WORDS
    public static final String PANTS = "pants";
    public static final String OBFUSCATE = "obfuscate";
    public static final String HOP = "hop";

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

    public static Meta getMWMetaNullValues() {
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

    public static MWInputThesResWord getInputMWThesaurusWord() {
        return MWInputThesResWord
                .builder()
                .meta(getMWMeta())
                .hwi(getHwi())
                .fl(PART_OF_SPEECH_STRING)
                .sls(new String[]{VERB_TENSE_STRING_OF_D_LINK_PANT_PANT})
                .shortDef(new String[]{SHORT_DEFINITION_STRING})
                .build();
    }

    public static List<MWInputThesResWord> getInputMWThesaurusWordList() {
        return Arrays.asList(getInputMWThesaurusWord(),
                getInputMWThesaurusWord());
    }

    public static MWInputThesResWord getInputMWThesaurusWordShortDefNull() {
        return MWInputThesResWord
                .builder()
                .meta(getMWMeta())
                .hwi(getHwi())
                .fl(PART_OF_SPEECH_STRING)
                .sls(new String[]{VERB_TENSE_STRING_OF_D_LINK_PANT_PANT})
                .build();
    }

    public static MWInputThesResWord getInputMWThesaurusWordMetaNull() {
        return MWInputThesResWord
                .builder()
                .meta(null)
                .hwi(getHwi())
                .fl(PART_OF_SPEECH_STRING)
                .sls(new String[]{VERB_TENSE_STRING_OF_D_LINK_PANT_PANT})
                .shortDef(new String[]{SHORT_DEFINITION_STRING})
                .build();
    }

    public static MWOutputThesResWord getMWOutputThesResWord() {
        return MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(getInputMWThesaurusWord());
    }

    public static MWOutputThesRes getMWOutputThesRes() {
        return MWOutputThesRes.builder()
                .search(WORD)
                .words(Arrays.asList(getMWOutputThesResWord(),
                        getMWOutputThesResWord()))
                .build();
    }

    //MW Service output
    public static List<MWOutputThesRes> getMWOutputThesResList() {
        return Arrays.asList(getMWOutputThesRes(),
                getMWOutputThesRes());
    }

    public static String getMWJson(String word){
        return switch (word) {
            case "obfuscate" ->
                    "[{\"meta\":{\"id\":\"obfuscate\",\"uuid\":\"78c30621-1900-4de0-9a08-7bcdffbd6212\",\"src\":\"coll_thes\",\"section\":\"alpha\",\"target\":{\"tuuid\":\"9ee89e5d-2662-4c2d-9537-7620be175b76\",\"tsrc\":\"collegiate\"},\"stems\":[\"obfuscate\",\"obfuscated\",\"obfuscates\",\"obfuscating\",\"obfuscation\",\"obfuscations\",\"obfuscatory\"],\"syns\":[[\"becloud\",\"befog\",\"blur\",\"cloud\",\"confuse\",\"fog\",\"muddy\"]],\"ants\":[[\"clarify\",\"clear (up)\",\"illuminate\"]],\"offensive\":false},\"hwi\":{\"hw\":\"obfuscate\"},\"fl\":\"verb\",\"def\":[{\"sseq\":[[[\"sense\",{\"dt\":[[\"text\",\"to make (something) unclear to the understanding \"],[\"vis\",[{\"t\":\"irrelevant matters that only serve to {it}obfuscate{\\/it} the fundamental issue of guilt or innocence\"}]]],\"syn_list\":[[{\"wd\":\"becloud\"},{\"wd\":\"befog\"},{\"wd\":\"blur\"},{\"wd\":\"cloud\"},{\"wd\":\"confuse\"},{\"wd\":\"fog\"},{\"wd\":\"muddy\"}]],\"rel_list\":[[{\"wd\":\"complicate\"},{\"wd\":\"perplex\"},{\"wd\":\"sophisticate\"}],[{\"wd\":\"entangle\"},{\"wd\":\"snarl\"},{\"wd\":\"tangle\"}],[{\"wd\":\"disarrange\"},{\"wd\":\"disarray\"},{\"wd\":\"discompose\"},{\"wd\":\"dishevel\"},{\"wd\":\"disorder\"},{\"wd\":\"disrupt\"},{\"wd\":\"disturb\"},{\"wd\":\"jumble\"},{\"wd\":\"mess (up)\"},{\"wd\":\"mix (up)\"},{\"wd\":\"muddle\"},{\"wd\":\"scramble\"},{\"wd\":\"shuffle\"},{\"wd\":\"tousle\"},{\"wd\":\"upset\"}]],\"near_list\":[[{\"wd\":\"simplify\"},{\"wd\":\"streamline\"}],[{\"wd\":\"disentangle\"},{\"wd\":\"straighten (out)\"},{\"wd\":\"undo\"},{\"wd\":\"unravel\"},{\"wd\":\"unscramble\"},{\"wd\":\"untangle\"}],[{\"wd\":\"decipher\"},{\"wd\":\"decode\"}],[{\"wd\":\"analyze\"},{\"wd\":\"break down\"}]],\"ant_list\":[[{\"wd\":\"clarify\"},{\"wd\":\"clear (up)\"},{\"wd\":\"illuminate\"}]]}]]]}],\"shortdef\":[\"to make (something) unclear to the understanding\"]}]";
            case "pants" -> "pants";
            default -> "none";
        };
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

    public static RBInputRhymeResItem getRBRhymeResItemVariablesSet() {
        RBInputRhymeResItem item = RBInputRhymeResItem
                .builder()
                .word(WORD)
                .freq(HIGH_FREQ)
                .score(HIGH_SCORE)
                .flags(ALL_FLAGS)
                .build();

        return item.setFlagVariables();
    }

    public static List<RBInputRhymeResItem> getRBInputRhymeResItemList() {
        return Arrays.asList(getRBRhymeResItem(),
                getRBRhymeResItem());
    }

    //RB Output
    public static RBOutputRhymeRes getRBOutputRhymeRes() {
        return RBOutputRhymeRes
                .builder()
                .search(WORD)
                .rhymes(getRBOutputRhymeResItemList())
                .build();
    }

    //RB Service Rhymes output
    public static List<RBOutputRhymeRes> getRBOutputRhymeResList() {
        return Arrays.asList(getRBOutputRhymeRes(),
                getRBOutputRhymeRes());
    }

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

    public static List<RBOutputRhymeResItem> getRBOutputRhymeResItemList() {
        return Arrays.asList(getRhymeBrainOutputResItem(),
                getRhymeBrainOutputResItem());
    }

    //RB short output
    public static RBShortOutputRhymeResItem getRBShortOutputRhymeResItem() {
        return RBShortOutputRhymeResItem
                .builder()
                .word(WORD)
                .freq(HIGH_FREQ)
                .score(HIGH_SCORE)
                .isOffensive(true)
                .build();
    }
}
