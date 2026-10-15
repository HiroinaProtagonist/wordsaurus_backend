package com.hiroinaprotagonist.wordsaurus.unit.rest.model.input.rhymebrainapi.response;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBInputRhymeResItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.getRBRhymeResItem;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RBInputRhymeResItemTest {

    private static RBInputRhymeResItem item;

    @BeforeEach
    void setup() {
        item = getRBRhymeResItem();
    }

    @Test
    void positive_test_flagIsOffensive() {
        assertTrue(item.flagIsOffensive());

        item.setFlags("bc");
        assertFalse(item.flagIsOffensive());

        item.setFlags(null);
        assertFalse(item.flagIsOffensive());
    }

    @Test
    void positive_test_flagIsDictionaryWord() {
        assertTrue(item.flagIsDictionaryWord());

        item.setFlags("ac");
        assertFalse(item.flagIsDictionaryWord());

        item.setFlags(null);
        assertFalse(item.flagIsDictionaryWord());
    }

    @Test
    void positive_test_flagHasKnownPronunciation() {
        assertTrue(item.flagIsKnownPronunciation());

        item.setFlags("ab");
        assertFalse(item.flagIsKnownPronunciation());

        item.setFlags(null);
        assertFalse(item.flagIsKnownPronunciation());
    }

    @Test
    void positive_setFlagVariables() {
        assertNull(item.getIsOffensive());
        assertNull(item.getIsDictionaryWord());
        assertNull(item.getIsKnownPronunciation());

        item.setFlagVariables();

        assertTrue(item.flagIsOffensive());
        assertTrue(item.flagIsDictionaryWord());
        assertTrue(item.flagIsKnownPronunciation());

        item.setFlags("");
        item.setFlagVariables();

        assertFalse(item.getIsOffensive());
        assertFalse(item.getIsDictionaryWord());
        assertFalse(item.getIsKnownPronunciation());
    }
}
