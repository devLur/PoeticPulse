package com.devlu.poeticpulse.models;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PoemLine")
public class PoemLine {
    @Id
    private String id;
    private final String line;
    private final String author;
    private final String title;

    public PoemLine(String line, String author, String title) {
        this.line = line;
        this.author = author;
        this.title = title;
    }

    public String getLine() {
        return line;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PoemLine poemLine = (PoemLine) o;
        return Objects.equals(line, poemLine.line)
                && Objects.equals(author, poemLine.author)
                && Objects.equals(title, poemLine.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, author, title);
    }
}
