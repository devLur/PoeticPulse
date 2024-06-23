package com.devlu.poeticpulse.services;

import static org.junit.jupiter.api.Assertions.*;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.persistence.MongoTestContainerConfig;
import com.devlu.poeticpulse.persistence.PoemRepository;
import com.devlu.poeticpulse.persistence.TestDataBuilder;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ContextConfiguration(classes = MongoTestContainerConfig.class)
class PoemServiceTest {
    @Autowired
    PoemRepository poemRepository;
    PoemService poemService;

    @BeforeEach
    void setUp() {
        List<Poem> poems = TestDataBuilder.getListOfTestPoems();
        poemRepository.saveAll(poems);
        poemService = new PoemService(poemRepository);
    }

    @Test
    void testGetPoemById() {
        var expected = poemRepository.findAll().getFirst();
        var underTest = poemService.getPoemById(expected.getId());

        assertEquals(underTest, expected);
    }

    @Test
    void testUpdatePoem() {
        var expected = poemRepository.findAll().getFirst();
        expected.setTitle("Updated Title");
        poemService.updatePoem(expected.getId(), expected);

        var underTest = poemRepository.findById(expected.getId()).orElse(null);
        assertEquals(underTest, expected);
    }

    @Test
    void testDeletePoem() {
        var expected = poemRepository.findAll().getFirst();
        poemService.deletePoem(expected.getId());

        var underTest = poemRepository.findById(expected.getId());
        assertTrue(underTest.isEmpty());
    }

    @Test
    void getAllPoems() {
        var expected = poemRepository.findAll();
        var underTest = poemService.getAllPoems();

        assertEquals(underTest, expected);
    }

    @Test
    void createPoem() {
        var expected = TestDataBuilder.getTestPoem();
        var underTest = poemService.createPoem(expected);

        assertEquals(underTest, expected);
    }

    @AfterEach
    void tearDown() {
        poemRepository.deleteAll();
    }
}