package com.devlu.poeticpulse.persistence;

import com.devlu.poeticpulse.models.Poem;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ContextConfiguration(classes = MongoTestContainerConfig.class)
class PoemRepositoryTest {
    @Autowired
    PoemRepository poemRepository;

    @BeforeEach
    void setUp() {
        List<Poem> poems = TestDataBuilder.getListOfTestPoems();
        poemRepository.saveAll(poems);
    }

    @Test
    void findByTitle() {
        Assertions.assertThat(poemRepository.findByTitle("Test Poem 1")).isNotNull();
    }

    @Test
    void findByTitleNotFound() {
        Assertions.assertThat(poemRepository.findByTitle("Nonexistent Poem")).isNull();
    }

    @AfterEach
    void tearDown() {
        poemRepository.deleteAll();
    }
}