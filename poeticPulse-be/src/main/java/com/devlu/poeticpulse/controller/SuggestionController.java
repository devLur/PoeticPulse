package com.devlu.poeticpulse.controller;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.services.SuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/random")
    public ResponseEntity<Poem> getRandomSuggestion() {
        Poem suggestion = suggestionService.getSuggestion();
        return ResponseEntity.ok(suggestion);
    }
}
