package com.epam.uladzislau.song;

import com.epam.uladzislau.song.model.Song;
import com.epam.uladzislau.song.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class SongApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongApplication.class, args);
    }

    @Autowired
    private SongRepository songRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        songRepository.save(new Song(1L, "TITLE", "ALBUM", "CREATOR", "FORMAT"));
    }
}
