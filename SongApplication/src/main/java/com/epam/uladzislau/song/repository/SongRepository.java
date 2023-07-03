package com.epam.uladzislau.song.repository;


import com.epam.uladzislau.song.model.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
