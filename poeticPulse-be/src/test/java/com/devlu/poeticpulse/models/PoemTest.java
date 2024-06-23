package com.devlu.poeticpulse.models;

import static org.junit.jupiter.api.Assertions.*;

import com.devlu.poeticpulse.persistence.TestDataBuilder;
import org.junit.jupiter.api.Test;

class PoemTest {
    @Test
    void testEqualsTrue() {
        Poem poem = TestDataBuilder.getTestPoem();
        Poem toCompare = new Poem(poem.getTitle(), poem.getLines());

        assertEquals(poem, toCompare);
        assertEquals(poem.hashCode(), toCompare.hashCode());
    }

    @Test
    void testEqualsFalse() {
        Poem poem = TestDataBuilder.getTestPoem();
        Poem toCompare = TestDataBuilder.getTestPoem();
        if (poem.getTitle().equals(toCompare.getTitle())) {
            toCompare.setTitle(toCompare.getTitle() + "different");
        }

        assertNotEquals(poem, toCompare);
        assertNotEquals(poem.hashCode(), toCompare.hashCode());
    }

    @Test
    void testEqualsNullFalse() {
        Poem poem = TestDataBuilder.getTestPoem();

        assertNotEquals(null, poem);
    }
}
