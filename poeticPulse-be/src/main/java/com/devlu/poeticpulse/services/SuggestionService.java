package com.devlu.poeticpulse.services;

import com.devlu.poeticpulse.clients.poetrydb.DtoConverter;
import com.devlu.poeticpulse.clients.poetrydb.PoetryClientBuilder;
import com.devlu.poeticpulse.models.Poem;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {
    PoetryClientBuilder poetryClientBuilder;

    public SuggestionService(PoetryClientBuilder poetryClientBuilder) {
        this.poetryClientBuilder = poetryClientBuilder;
    }

    public List<Poem> getSuggestions(int amount) {
        return poetryClientBuilder.buildClient()
                .getRandomPoems(amount)
                .stream()
                .map(DtoConverter::convert)
                .toList();
    }

    public Poem getSuggestion() {
        return getSuggestions(1).getFirst();
    }
}
