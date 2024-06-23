package com.devlu.poeticpulse.clients.poetrydb.models;

import java.util.List;

public class Poem {
    String title;
    String author;
    List<String> lines;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLines() {
        return lines;
    }

    public Poem(String title, String author, List<String> lines) {
        this.title = title;
        this.author = author;
        this.lines = lines;
    }

    public Poem() {}
}
