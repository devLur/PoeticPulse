package com.devlu.poeticpulse.models;

import static org.junit.jupiter.api.Assertions.*;

import com.devlu.poeticpulse.persistence.TestDataBuilder;
import org.junit.jupiter.api.Test;

class PoemLineTest {
    @Test
    void testEqualsTrue() {
        PoemLine poemLine = TestDataBuilder.getTestPoem().getLines().getFirst();
        PoemLine toCompare = new PoemLine(
                poemLine.getLine(),
                poemLine.getAuthor(),
                poemLine.getTitle());

        assertEquals(poemLine, toCompare);
        assertEquals(poemLine.hashCode(), toCompare.hashCode());
    }

    @Test
    void testEqualsFalse() {
        PoemLine poemLine = TestDataBuilder.getTestPoem().getLines().getFirst();
        PoemLine toCompare = new PoemLine("different", "different", "different");

        assertNotEquals(poemLine, toCompare);
        assertNotEquals(poemLine.hashCode(), toCompare.hashCode());
    }

    @Test
    void testEqualsNullFalse() {
        PoemLine poemLine = TestDataBuilder.getTestPoem().getLines().getFirst();

        assertNotEquals(null, poemLine);
    }



}
