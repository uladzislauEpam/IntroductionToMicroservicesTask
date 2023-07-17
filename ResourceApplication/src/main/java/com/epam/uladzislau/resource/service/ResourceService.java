package com.epam.uladzislau.resource.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.epam.uladzislau.resource.feign.ServiceSongs;
import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.model.Song;
import com.epam.uladzislau.resource.repository.ResourceRepository;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;

@Service
public class ResourceService {

    private static final String URL = "localhost:8090/api/song";
//    private static final String URL = "http://host.docker.internal:8090/api/song";
//    private static final String URL = "apps:8090/api/song";

    @Autowired
    private ServiceSongs feign;

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
//                WebClient.builder().build()
//                    .delete()
//                    .uri(URL + "/" + resource.get().getMetadataId())
//                    .retrieve();
//                deletedIds.add(id);
                feign.deleteSong( resource.get().getMetadataId());
            }
        }
        return deletedIds;
    }

    public Long upload(MultipartFile file) {
        long id = -1;
        try {
            InputStream stream = file.getInputStream();

            Parser pparser = new AutoDetectParser();
            ContentHandler contentHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            pparser.parse(stream, contentHandler, metadata, new ParseContext());
            stream.close();

            Resource resource = resourceRepository.save(new Resource(file.getBytes()));
            id = resource.getId();

//            Map<String, String> body = new HashMap<>();
//            body.put("title", metadata.get("title"));
//            body.put("resourceId", Long.toString(resource.getId()));
//
//            var song = WebClient.builder().build()
//                .post()
//                .uri(URL)
//                .body(BodyInserters.fromValue(body))
//                .retrieve()
//                .bodyToMono(Song.class)
//                .block();
            var song = feign.postSong(new Song(resource.getId(), metadata.get(TikaCoreProperties.TITLE)));
            if (song != null) {
                resource.setMetadataId(song.getId());
            } else {
                throw new RuntimeException("Cannot save metadata");
            }
            resourceRepository.save(resource);

            return resource.getId();
        } catch (Exception e) {
            if(id != -1) {
                resourceRepository.deleteById(id);
            }
            System.out.println("Cannot process file: Nested exception is " + e.getMessage());
            throw new RuntimeException("Cannot process file: Nested exception is " + e.getMessage());
        }
    }
}
