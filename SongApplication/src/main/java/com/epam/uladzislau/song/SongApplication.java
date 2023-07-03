package com.epam.uladzislau.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SongApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongApplication.class, args);
    }
}
