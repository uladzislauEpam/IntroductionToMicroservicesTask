package com.epam.uladzislau.song.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Entity
@Table(name = "song")
@AllArgsConstructor
public class Song {

    public Song(){}

    public Song(Long resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public Song(Long id, Long resourceId, String title) {
        this.id = id;
        this.resourceId = resourceId;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "resource_id")
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
