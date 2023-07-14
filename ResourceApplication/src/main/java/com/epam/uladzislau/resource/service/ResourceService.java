package com.epam.uladzislau.resource.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.model.Song;
import com.epam.uladzislau.resource.repository.ResourceRepository;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ResourceService {

    private static final String URL = "localhost:8090/api/song";

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    public Resource get(Long id) {
        return resourceRepository.getOne(id);
    }

    public List<Long> delete(List<Long> ids) {
        List<Long> deletedIds = new ArrayList<>();
        for (var id : ids) {
            var resource = resourceRepository.findById(id);
            if(resource.isPresent()) {
                resourceRepository.deleteById(id);
                WebClient.builder().build()
                    .delete()
                    .uri(URL + "/" + resource.get().getMetadataId())
                    .retrieve();
                deletedIds.add(id);
            }
        }
        return deletedIds;
    }

    public Long upload(MultipartFile file) {
        try {
            Mp3Parser parser = new Mp3Parser();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();
            BodyContentHandler handler = new BodyContentHandler();
            InputStream stream = file.getInputStream();
            parser.parse(stream, handler, metadata, context);
            stream.close();

            Resource resource = resourceRepository.save(new Resource(file.getBytes()));

            Map<String, String> body = new HashMap<>();
            body.put("title", metadata.get("title"));
            body.put("resourceId", Long.toString(resource.getId()));

            var song = WebClient.builder().build()
                .post()
                .uri(URL)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(Song.class)
                .block();
            if (song != null) {
                resource.setMetadataId(song.getId());
            } else {
                throw new RuntimeException("Cannot save metadata");
            }
            resourceRepository.save(resource);

            return resource.getId();
        } catch (Exception e) {
            System.out.println("Cannot process file: Nested exception is " + e.getMessage());
            throw new RuntimeException("Cannot process file: Nested exception is " + e.getMessage());
        }
    }
}
