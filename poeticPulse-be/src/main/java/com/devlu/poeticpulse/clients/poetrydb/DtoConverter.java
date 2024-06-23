package com.devlu.poeticpulse.clients.poetrydb;

import com.devlu.poeticpulse.clients.poetrydb.models.Poem;

public class DtoConverter {

    private DtoConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static com.devlu.poeticpulse.models.Poem convert(Poem poem) {
        return new com.devlu.poeticpulse.models.Poem(
                poem.getTitle(),
                poem.getAuthor(),
                poem.getLines());
    }
}
