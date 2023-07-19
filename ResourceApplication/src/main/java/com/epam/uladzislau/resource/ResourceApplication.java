package com.epam.uladzislau.resource;

import java.io.File;
import java.io.FileInputStream;

import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.repository.ResourceRepository;
import com.epam.uladzislau.resource.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableFeignClients
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    @Value("${resource.file.path}")
    private String filePath;
    @Value("${resource.file.name}")
    private String fileName;

    @Autowired
    private ResourceRepository resourceRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile(fileName, getClass().getResourceAsStream(filePath));
        var resource = resourceRepository.findById(1L)
            .orElse(new Resource(1L, null, 1L));
        resource.setAudioBytes(multipartFile.getBytes());
        resourceRepository.save(resource);
    }
}
