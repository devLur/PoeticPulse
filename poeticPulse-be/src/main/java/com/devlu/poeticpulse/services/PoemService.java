package com.devlu.poeticpulse.services;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.persistence.PoemRepository;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PoemService {
    private final PoemRepository poemRepository;

    public PoemService(PoemRepository poemRepository) {
        this.poemRepository = poemRepository;
    }

    public Poem getPoemById(String id) {
        return poemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Poem not "
                        + "found with id: " + id));
    }

    public Poem updatePoem(String id, Poem newPoem) {
        Poem poem = poemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Poem not found "
                        + "when trying to update poem with id: " + id));
        poem.setTitle(newPoem.getTitle());
        poem.setLines(newPoem.getLines());
        return poemRepository.save(poem);
    }

    public void deletePoem(String id) {
        if (!poemRepository.existsById(id)) {
            throw new IllegalArgumentException("Poem not found when "
                    + "trying to delete poem with id: " + id);
        }
        poemRepository.deleteById(id);
    }

    public List<Poem> getAllPoems() {
        return poemRepository.findAll();
    }

    public Poem createPoem(Poem poem) {
        try {
            return poemRepository.save(poem);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("A database constraint "
                    + "was violated while trying to create poem", e);
        }
    }
}
