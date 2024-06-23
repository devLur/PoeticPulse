package com.devlu.poeticpulse.clients.poetrydb;

import static org.junit.jupiter.api.Assertions.*;

import com.devlu.poeticpulse.clients.poetrydb.models.Poem;
import org.junit.jupiter.api.Test;

class DtoConverterTest {
    @Test
    void testConvertPoem() {
        Poem inputPoem = TestDataBuilder.getPoems(1).getFirst();
        com.devlu.poeticpulse.models.Poem outputPoem = DtoConverter.convert(inputPoem);

        assertEquals(inputPoem.getTitle(), outputPoem.getTitle());
        assertEquals(inputPoem.getAuthor(), outputPoem.getLines().getFirst().getAuthor());
        assertEquals(inputPoem.getTitle(), outputPoem.getLines().getFirst().getTitle());
        assertEquals(inputPoem.getLines().getFirst(), outputPoem.getLines().getFirst().getLine());
    }
}