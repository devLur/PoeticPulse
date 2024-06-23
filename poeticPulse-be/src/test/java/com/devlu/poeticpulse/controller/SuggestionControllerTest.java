package com.devlu.poeticpulse.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.persistence.TestDataBuilder;
import com.devlu.poeticpulse.services.SuggestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SuggestionControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    SuggestionService suggestionService;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetRandomSuggestionReturnsPoem() throws Exception {
        // Arrange
        Poem expectedPoem = TestDataBuilder.getTestPoem();
        when(suggestionService.getSuggestion()).thenReturn(expectedPoem);

        // Act & Assert
        mockMvc.perform(get("/suggestions/random")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedPoem)));
    }
}