package com.devlu.poeticpulse.controller;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.services.PoemService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poems")
public class PoemController {
    private final PoemService poemService;

    public PoemController(PoemService poemService) {
        this.poemService = poemService;
    }

    @GetMapping
    public ResponseEntity<List<Poem>> getAllPoems() {
        List<Poem> poems = poemService.getAllPoems();
        return ResponseEntity.ok(poems);
    }

    @PostMapping
    public ResponseEntity<Poem> createPoem(@RequestBody Poem poem) {
        Poem createdPoem = poemService.createPoem(poem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poem> getPoemById(@PathVariable String id) {
        Poem poem = poemService.getPoemById(id);
        return ResponseEntity.ok(poem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poem> updatePoem(@PathVariable String id, @RequestBody Poem newPoem) {
        Poem updatedPoem = poemService.updatePoem(id, newPoem);
        return ResponseEntity.ok(updatedPoem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoem(@PathVariable String id) {
        poemService.deletePoem(id);
        return ResponseEntity.ok().build();
    }
}