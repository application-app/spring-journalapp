package net.engineeringdigest.journalApp.types;

import org.springframework.stereotype.Component;

@Component
public class Journals {
    private Long id;
    private String name;
    private String mood;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
