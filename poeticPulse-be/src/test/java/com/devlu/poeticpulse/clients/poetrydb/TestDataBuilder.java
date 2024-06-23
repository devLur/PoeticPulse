package com.devlu.poeticpulse.clients.poetrydb;

import com.devlu.poeticpulse.clients.poetrydb.models.Poem;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    public static List<Poem> getPoems(int count) {
        List<Poem> poems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            poems.add(new Poem(
                    "Test title " + i,
                    "Test Author " + i,
                    List.of("Line 1", "Line 2")));
        }
        return poems;
    }
}
