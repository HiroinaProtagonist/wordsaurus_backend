package com.hiroinaprotagonist.wordsaurus.unit.rest.model.input.mwapi.converter;


import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.converter.MWtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesResWord;
import org.junit.jupiter.api.Test;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;


class MWtoResMapperTest {

    @Test
    void positive_test() {
        //given
        MWThesaurusWord mapInput = getInputMWThesaurusWord();

        //when
        MWOutputThesResWord mapOutput = MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(mapInput);

        //then
        assertEquals(FIRST_ANTONYM, mapInput.meta().ants()[0][0]);
        assertEquals(mapInput.meta().ants()[0][0], mapOutput.getMeta().ants()[0][0]);
        assertEquals(mapInput.meta().ants()[0][0], mapOutput.getAntonyms()[0][0]);

        assertEquals(PART_OF_SPEECH_STRING, mapInput.fl());
        assertEquals(mapInput.fl(), mapOutput.getPartOfSpeech());

        assertEquals(mapInput.meta().section(), mapOutput.getMetaSection());
        assertEquals(mapInput.meta().target(), mapOutput.getMwTarget());
        assertEquals(mapInput.hwi().hw(), mapOutput.getHwi().hw());

        //rhyming is not set via mapper
        assertEquals(null, mapOutput.getRhyming());
    }

    void positive_test_null_array_values() {
        //given
        MWThesaurusWord mapInput = getInputMWThesaurusWord();

        //when
        MWOutputThesResWord mapOutput = MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(mapInput);

        //then
        assertEquals(FIRST_ANTONYM, mapInput.meta().ants()[0][0]);
        assertEquals(mapInput.meta().ants()[0][0], mapOutput.getMeta().ants()[0][0]);
        assertEquals(mapInput.meta().ants()[0][0], mapOutput.getAntonyms()[0][0]);

        assertEquals(PART_OF_SPEECH_STRING, mapInput.fl());
        assertEquals(mapInput.fl(), mapOutput.getPartOfSpeech());

        assertEquals(mapInput.meta().section(), mapOutput.getMetaSection());
        assertEquals(mapInput.meta().target(), mapOutput.getMwTarget());
        assertEquals(mapInput.hwi().hw(), mapOutput.getHwi().hw());

        assertNotNull(mapInput.shortDef());
        assertNotNull(mapOutput.getShortDef());

        //rhyming is not set via mapper
        assertNull(mapOutput.getRhyming());
    }

    @Test
    void positive_null_input() {
        //given null input
        //when mapped
        //then null output
        assertNull(MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(null));
    }

    @Test
    void positive_shortDef_null_input() {
        //given
        MWThesaurusWord mapInput = getInputMWThesaurusWordShortDefNull();

        //when
        MWOutputThesResWord mapOutput = MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(mapInput);

        //then
        assertNull(mapInput.shortDef());
        assertNull(MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(mapInput).getShortDef());

    }

    @Test
    void positive_meta_null_input() {
        //given
        MWThesaurusWord mapInput = getInputMWThesaurusWordMetaNull();

        //when
        MWOutputThesResWord mapOutput = MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(mapInput);

        //then
        assertNull(MWtoResMapper.MAPPER_INSTANCE
                .mwToResMapper(mapInput).getMeta());
    }
}
