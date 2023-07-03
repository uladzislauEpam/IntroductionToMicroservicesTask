package com.epam.uladzislau.resource.model;

public class Song {

    public Song(){}

    public Song(Long id) {
        this.id = id;
    }

    public Song(Long resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public Song(Long id, Long resourceId, String title) {
        this.id = id;
        this.resourceId = resourceId;
        this.title = title;
    }

    private Long id;

    private String title;

    private Long resourceId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
