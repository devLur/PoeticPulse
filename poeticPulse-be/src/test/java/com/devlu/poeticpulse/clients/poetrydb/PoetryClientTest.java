package com.devlu.poeticpulse.clients.poetrydb;

import static org.junit.jupiter.api.Assertions.*;

import com.devlu.poeticpulse.clients.poetrydb.models.Poem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JacksonAutoConfiguration.class)
class PoetryClientTest {
    @Autowired
    private ObjectMapper mapper;
    PoetryClient poetrydbClient;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        MockClient mockedClient = new MockClient()
                .ok(HttpMethod.GET, "/random/1", mapper.writeValueAsString(TestDataBuilder.getPoems(1)))
                .ok(HttpMethod.GET, "/random/3", mapper.writeValueAsString(TestDataBuilder.getPoems(3)))
                .add(HttpMethod.GET, "/random/13337", 500)
                .add(HttpMethod.GET, "/random/13338", 400);

        poetrydbClient = PoetryClientBuilder
                .getDefaultFeignBuilder()
                .client(mockedClient)
                .target(new MockTarget<>(PoetryClient.class));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void testGetRandomPoems(int amount) {
        List<Poem> poems = poetrydbClient.getRandomPoems(amount);

        assertEquals(poems.size(), amount);
    }

    @Test
    void testHandleBadRequest() {
        assertThrows(FeignException.BadRequest.class, () ->
                poetrydbClient.getRandomPoems(13338)
        );
    }

    @Test
    void testHandleInternalServerError() {
        assertThrows(FeignException.InternalServerError.class, () ->
                poetrydbClient.getRandomPoems(13337)
        );
    }
}