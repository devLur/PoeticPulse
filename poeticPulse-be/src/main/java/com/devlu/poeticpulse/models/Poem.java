package com.devlu.poeticpulse.models;

import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Poem")
public class Poem {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private List<PoemLine> lines;

    @PersistenceCreator
    public Poem(String title, List<PoemLine> lines) {
        this.title = title;
        this.lines = lines;
    }

    public Poem(String title, String author, List<String> lines) {
        this(title, lines.stream()
                .map(line -> new PoemLine(line, author, title))
                .toList());
    }

    public Poem() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<PoemLine> getLines() {
        return lines;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLines(List<PoemLine> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Poem poem = (Poem) o;
        return Objects.equals(title, poem.title) && Objects.equals(lines, poem.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, lines);
    }
}
