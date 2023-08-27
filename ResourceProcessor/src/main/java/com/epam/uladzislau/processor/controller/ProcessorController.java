package com.epam.uladzislau.processor.controller;

import java.util.List;

import com.epam.uladzislau.processor.feign.ServiceSongs;
import com.epam.uladzislau.processor.model.Song;
import com.epam.uladzislau.processor.service.ProcessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/processor")
public class ProcessorController {

    @Autowired
    ServiceSongs feign;

    @Autowired
    private ProcessorService processorService;

    @PostMapping
    ResponseEntity<Long> send(@RequestParam Song song) {
        feign.postSong(song);
        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    Long delete(@PathVariable Long id) {
        System.out.println(id);
        return id;
//        ids.forEach(id -> feign.deleteSong(id));
//        return ids;
    }
}
