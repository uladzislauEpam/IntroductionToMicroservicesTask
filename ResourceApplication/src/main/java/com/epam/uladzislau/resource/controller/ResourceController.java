package com.epam.uladzislau.resource.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.uladzislau.resource.feign.ServiceSongs;
import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private ServiceSongs feign;
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/all")
    List<Resource> getAll() {
        return resourceService.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Map<String, String>> get(@PathVariable Long id) {
        Map<String, String> object = new HashMap<>();
        var resource = resourceService.get(id);
        object.put("id", Long.toString(resource.getId()));
        object.put("title", Arrays.toString(resource.getAudioBytes()));
        object.put("metadataId", Long.toString(resource.getMetadataId()));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping
    Long upload(@RequestBody MultipartFile file) {
        return resourceService.upload(file);
    }

    @DeleteMapping("/{ids}")
    List<Long> delete(@PathVariable List<Long> ids) {
        return resourceService.delete(ids);
    }

    @GetMapping("/getsong")
    ResponseEntity<Map<String, String>> getsong() {
        Map<String, String> object = new HashMap<>();
        var song = feign.getSong(1);
        object.put("id", Long.toString(song.getId()));
        System.out.println("========================= " + song);
        object.put("title", song.getTitle());
        object.put("resourceId", Long.toString(song.getResourceId()));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

}
