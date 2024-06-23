package com.devlu.poeticpulse.clients.poetrydb;

import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PoetryClientBuilder {

    private final String poetrydbBaseUrl;
    private final boolean logDebugLevel;

    public PoetryClientBuilder(
            @Value("${poeticPulse.poetrydbAPI.baseUrl}") String poetrydbBaseUrl,
            @Value("${poeticPulse.poetrydbAPI.feign.debug}") Boolean logDebugLevel) {
        this.poetrydbBaseUrl = poetrydbBaseUrl;
        this.logDebugLevel = logDebugLevel;
    }

    public PoetryClient buildClient() {
        Level logLevel = Boolean.TRUE.equals(logDebugLevel) ? Level.FULL : Level.BASIC;
        return getDefaultFeignBuilder()
                .logger(new Slf4jLogger(PoetryClient.class))
                .logLevel(logLevel)
                .target(PoetryClient.class, poetrydbBaseUrl);
    }

    public static Feign.Builder getDefaultFeignBuilder() {
        return new Feign.Builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder());
    }
}
