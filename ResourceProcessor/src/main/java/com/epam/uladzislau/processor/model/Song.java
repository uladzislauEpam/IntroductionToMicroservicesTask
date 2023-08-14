package com.epam.uladzislau.processor.model;

public class Song {

    public Song(){}

    public Song(String title, String album, String creator, String format) {
        this.title = title;
        this.album = album;
        this.creator = creator;
        this.format = format;
    }

    public Song(Long id, String title, String album, String creator, String format) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.creator = creator;
        this.format = format;
    }

    private Long id;
    private String title;
    private String album;
    private String creator;
    private String format;

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getalbum() {
        return album;
    }
    public String getCreator() {
        return creator;
    }
    public String getFormat() {
        return format;
    }
}
