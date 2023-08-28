package com.epam.uladzislau.processor.feign;

import com.epam.uladzislau.processor.dto.SongDto;
import com.epam.uladzislau.processor.model.Song;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient("SongApplication")
public interface ServiceSongs {

    @RequestMapping("api/song/{id}")
    Song getSong(@PathVariable("id") long id);

    @PostMapping("api/song")
    Long postSong(@RequestBody SongDto song);

    @DeleteMapping("api/song/{id}")
    void deleteSong(@PathVariable("id") long id);

}