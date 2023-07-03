package com.epam.uladzislau.song.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.uladzislau.song.model.Song;
import com.epam.uladzislau.song.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAll() {
        return songRepository.findAll();
    }

    public Song get(Long id) {
        return songRepository.getOne(id);
    }

    public List<Long> delete(List<Long> ids) {
        List<Long> deletedIds = new ArrayList<>();
        for (var id : ids) {
            var resource = songRepository.findById(id);
            if(resource.isPresent()) {
                songRepository.deleteById(id);
                deletedIds.add(id);
            }
        }
        return deletedIds;
    }

    public Long upload(Song song) {
        return songRepository.save(song).getId();
    }
}
