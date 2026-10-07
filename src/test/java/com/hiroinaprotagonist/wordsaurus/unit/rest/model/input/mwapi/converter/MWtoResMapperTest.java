package com.hiroinaprotagonist.wordsaurus.unit.rest.model.input.mwapi.converter;


import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.converter.MWtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWThesResWord;
import org.junit.jupiter.api.Test;

import static com.hiroinaprotagonist.wordsaurus.test_data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MWtoResMapperTest {

    @Test
    void positive_test() {
        //given
        MWThesaurusWord mapInput = getInputMWThesaurusWord();

        //when
        MWThesResWord mapOutput = MWtoResMapper.MAPPER_INSTANCE.mwToResMapper(mapInput);

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
}
