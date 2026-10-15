package com.hiroinaprotagonist.wordsaurus.unit.rest.model.input.rhymebrainapi.converter;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.converter.RBtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeResItem;
import org.junit.jupiter.api.Test;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class RBtoResMapperTest {

    @Test
    void positive_test_variablesNotMapped() {
        //given
        RBInputRhymeResItem mapInput = getRBRhymeResItem();

        //when
        RBOutputRhymeResItem mapOutput = RBtoResMapper.MAPPER_INSTANCE.rbToResMapper(mapInput);

        //then
        assertEquals(WORD, mapInput.getWord());

        assertEquals(mapInput.getWord(), mapOutput.getWord());
        assertEquals(mapInput.getFreq(), mapOutput.getFreq());
        assertEquals(mapInput.getScore(), mapOutput.getScore());
        assertEquals(mapInput.getFlags(), mapOutput.getFlags());

        assertNull(mapInput.getIsOffensive());
        assertNull(mapInput.getIsDictionaryWord());
        assertNull(mapInput.getIsKnownPronunciation());

        assertFalse(mapOutput.isOffensive());
        assertFalse(mapOutput.isDictionaryWord());
        assertFalse(mapOutput.isKnownPronunciation());

        // TODO populate syllables
        assertNull(mapOutput.getSyllables());
    }

    @Test
    void positive_null_input() {
        //given null input
        //when mapped
        //then null output
        assertNull(RBtoResMapper.MAPPER_INSTANCE.rbToResMapper(null));
    }
}
