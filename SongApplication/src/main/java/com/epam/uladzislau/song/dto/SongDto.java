package com.epam.uladzislau.song.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
public class SongDto {
    private String title;
    private String album;
    private String creator;
    private String format;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public SongDto(String title, String album, String creator, String format) {
        this.title = title;
        this.album = album;
        this.creator = creator;
        this.format = format;
    }

    public SongDto() {}
}
