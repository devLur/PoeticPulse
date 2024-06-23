package com.devlu.poeticpulse.persistence;

import com.devlu.poeticpulse.models.Poem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoemRepository extends MongoRepository<Poem, String> {
    Poem findByTitle(String title);
}
