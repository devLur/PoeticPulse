package com.devlu.poeticpulse.persistence;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.models.PoemLine;
import java.util.Arrays;
import java.util.List;

public class TestDataBuilder {
    public static List<Poem> getListOfTestPoems() {
        return Arrays.asList(
                new Poem("Test Poem 1",
                        List.of(new PoemLine("Test line 1", "Test author 1", "Test title 1"))),
                new Poem("Test Poem 2",
                        List.of(new PoemLine("Test line 2", "Test author 2", "Test title 2"))),
                new Poem("Test Poem 3",
                        List.of(new PoemLine("Test line 3", "Test author 3", "Test title 3")))
        );
    }

    public static Poem getTestPoem() {
        return new Poem(
                "New Poem",
                List.of(new PoemLine("Test line", "Test author", "Test title"))
        );
    }
}
