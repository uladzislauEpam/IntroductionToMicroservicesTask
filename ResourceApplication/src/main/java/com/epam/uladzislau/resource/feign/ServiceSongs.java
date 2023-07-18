package com.epam.uladzislau.resource.feign;

import java.util.List;

import com.epam.uladzislau.resource.dto.SongDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("SongApplication")
public interface ServiceSongs {

    @PostMapping("api/song")
    Long postSong(@RequestBody SongDto song);

    @DeleteMapping("api/song/{id}")
    List<Long> deleteSong(@PathVariable("id") long id);

}