package com.devlu.poeticpulse.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devlu.poeticpulse.models.Poem;
import com.devlu.poeticpulse.persistence.PoemRepository;
import com.devlu.poeticpulse.persistence.TestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PoemControllerTest {
    @Autowired
    PoemRepository poemRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    List<Poem> testPoems;

    @BeforeEach
    void setUp() {
        testPoems = TestDataBuilder.getListOfTestPoems();
        poemRepository.saveAll(testPoems);
    }

    @Test
    void testGetAllPoems() throws Exception {
        var mvcResult = mockMvc.perform(get("/poems")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var content = mvcResult.getResponse().getContentAsString();
        var result = objectMapper.readValue(content, Poem[].class);
        assertEquals(testPoems.size(), result.length);
    }

    @Test
    void testGetPoemById() throws Exception {
        var testPoem = poemRepository.findAll().getFirst();

        mockMvc.perform(get("/poems/" + testPoem.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(objectMapper.readValue(
                        result.getResponse().getContentAsString(), Poem.class), testPoem))
                .andReturn();
    }

    @Test
    void testGetPoemByNonExistentId() throws Exception {
        String nonExistentId = "nonExistentId";

        mockMvc.perform(get("/poems/" + nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Poem not found with id: "
                        + nonExistentId, result.getResponse().getContentAsString()));
    }

    @Test
    void testCreatePoem() throws Exception {
        Poem poem = TestDataBuilder.getTestPoem();

        mockMvc.perform(post("/poems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(poem)))
                .andExpect(status().isCreated());

        assertTrue(poemRepository.findAll().contains(poem));
    }

    @Test
    void testDeletePoem() throws Exception {
        Poem poem = TestDataBuilder.getTestPoem();
        Poem savedPoem = poemRepository.save(poem);

        mockMvc.perform(delete("/poems/" + savedPoem.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Assertions.assertFalse(poemRepository.findById(savedPoem.getId()).isPresent());
    }

    @Test
    void testDeleteNonExistingPoem() throws Exception {
        String nonExistentId = "nonExistentId";

        mockMvc.perform(delete("/poems/" + nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Poem not found when"
                        + " trying to delete poem with id: "
                        + nonExistentId, result.getResponse().getContentAsString()));
    }

    @Test
    void testUpdatePoem() throws Exception {
        Poem poem = TestDataBuilder.getTestPoem();
        Poem savedPoem = poemRepository.save(poem);
        savedPoem.setTitle("Updated Poem");

        mockMvc.perform(put("/poems/" + savedPoem.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedPoem)))
                .andExpect(status().isOk());

        assertEquals(poemRepository.findById(savedPoem.getId())
                .map(Poem::getTitle)
                .orElse(null), savedPoem.getTitle());
    }

    @Test
    void testUpdateNonExistingPoem() throws Exception {
        String nonExistentId = "nonExistentId";
        Poem nonExistingPoemToUpdate = TestDataBuilder.getTestPoem();

        mockMvc.perform(put("/poems/" + nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nonExistingPoemToUpdate)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Poem not found when "
                        + "trying to update poem with id: "
                        + nonExistentId, result.getResponse().getContentAsString()));
    }

    @AfterEach
    void tearDown() {
        poemRepository.deleteAll();
    }
}
