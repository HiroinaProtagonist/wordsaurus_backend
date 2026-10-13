package com.hiroinaprotagonist.wordsaurus.unit.rest.model.input.rhymebrainapi.converter;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.converter.RBtoResMapper;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeResItem;
import org.junit.jupiter.api.Test;

import static com.hiroinaprotagonist.wordsaurus.test_data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RBtoResMapperTest {

    @Test
    void positive_test() {
        //given
        RBInputRhymeResItem mapInput = getRBRhymeResItem();

        //when
        RBOutputRhymeResItem mapOutput = RBtoResMapper.MAPPER_INSTANCE.rbToResMapper(mapInput);

        //then
        assertEquals(WORD, mapInput.getWord());

        assertEquals(mapInput.getWord(), mapOutput.getWord());
        assertEquals(true, mapInput.getIsOffensive());
        assertEquals(mapInput.getIsOffensive(), mapOutput.isOffensive());
        assertEquals(mapInput.getIsDictionaryWord(), mapOutput.isDictionaryWord());
        assertEquals(mapInput.getIsKnownPronunciation(), mapOutput.isKnownPronunciation());

//        //searchWord is not set via mapper
//        assertNull(mapOutput.getSearchWord());
    }
}
