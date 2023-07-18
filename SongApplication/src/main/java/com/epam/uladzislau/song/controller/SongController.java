package com.epam.uladzislau.song.controller;

import java.util.List;

import com.epam.uladzislau.song.model.Song;
import com.epam.uladzislau.song.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/all/{page}")
    Page<Song> getAll(@PathVariable int page) {
        return songService.getAll(page);
    }

    @GetMapping("/{id}")
    ResponseEntity<Song> get(@PathVariable Long id) {
        return new ResponseEntity<>(songService.get(id), HttpStatus.OK);
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
