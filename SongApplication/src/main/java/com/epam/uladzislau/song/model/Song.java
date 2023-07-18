package com.epam.uladzislau.song.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "song")
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "album")
    private String album;

    @Column(name = "creator")
    private String creator;

    @Column(name = "format")
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
