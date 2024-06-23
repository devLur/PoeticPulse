package com.devlu.poeticpulse.clients.poetrydb;

import com.devlu.poeticpulse.clients.poetrydb.models.Poem;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface PoetryClient {
    @RequestLine("GET /random/{count}")
    List<Poem> getRandomPoems(@Param("count") Integer count);
}
