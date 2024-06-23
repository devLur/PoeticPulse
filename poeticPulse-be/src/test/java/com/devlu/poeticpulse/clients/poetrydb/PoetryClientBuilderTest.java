package com.devlu.poeticpulse.clients.poetrydb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
class PoetryClientBuilderTest {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WireMockServer poetrydbService;
    @Autowired
    PoetryClientBuilder builder;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        poetrydbService.stubFor(WireMock
                .get(WireMock.urlEqualTo("/random/1")).willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(mapper.writeValueAsBytes(TestDataBuilder.getPoems(1)))));
    }

    @Test
    void testClient() {
        PoetryClient poetryClient = builder.buildClient();
        var result = poetryClient.getRandomPoems(1);

        Assertions.assertNotNull(result);
    }
}
