package com.devlu.poeticpulse.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.devlu.poeticpulse.clients.poetrydb.PoetryClient;
import com.devlu.poeticpulse.clients.poetrydb.PoetryClientBuilder;
import com.devlu.poeticpulse.clients.poetrydb.models.Poem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SuggestionServiceTest {

    @Mock
    PoetryClientBuilder poetryClientBuilder;
    @Mock
    PoetryClient poetryClient;
    AutoCloseable closeable;
    SuggestionService suggestionService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        when(poetryClientBuilder.buildClient()).thenReturn(poetryClient);
        suggestionService = new SuggestionService(poetryClientBuilder);
    }

    @Test
    void testGetSuggestions() {
        List<Poem> poems = Arrays.asList(
                new Poem("Test Poem 1", "Test Author 1", new ArrayList<>()),
                new Poem("Test Poem 2", "Test Author 2", new ArrayList<>())
        );
        when(poetryClient.getRandomPoems(2)).thenReturn(poems);
        List<com.devlu.poeticpulse.models.Poem> result = suggestionService.getSuggestions(2);

        assertEquals(2, result.size());
        assertEquals("Test Poem 1", result.get(0).getTitle());
        assertEquals("Test Poem 2", result.get(1).getTitle());
    }

    @Test
    void testGetSuggestion() {
        Poem poem = new Poem("Test Poem", "Test Author", new ArrayList<>());
        when(poetryClient.getRandomPoems(1)).thenReturn(Collections.singletonList(poem));
        com.devlu.poeticpulse.models.Poem result = suggestionService.getSuggestion();

        assertEquals("Test Poem", result.getTitle());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}