package com.epam.uladzislau.song.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.uladzislau.song.model.Song;
import com.epam.uladzislau.song.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/all")
    List<Song> getAll() {
        return songService.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Map<String, String>> get(@PathVariable Long id) {
        Map<String, String> object = new HashMap<>();
        var song = songService.get(id);
        object.put("id", Long.toString(song.getId()));
        object.put("title", song.getTitle());
        object.put("resourceId", Long.toString(song.getResourceId()));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping
    Long upload(@RequestBody Song song) {
        return songService.upload(song);
    }

    @DeleteMapping("/{ids}")
    List<Long> delete(@PathVariable List<Long> ids) {
        return songService.delete(ids);
    }

}
