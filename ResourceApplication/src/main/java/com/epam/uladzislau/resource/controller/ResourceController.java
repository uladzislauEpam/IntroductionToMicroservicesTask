package com.epam.uladzislau.resource.controller;

import java.util.List;

import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.service.ResourceService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
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

    ResourceController(){}

    ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/all/{page}")
    Page<Resource> getAll(@PathVariable int page) {
        return resourceService.getAll(page);
    }

    @GetMapping("/{id}")
    ResponseEntity<Resource> get(@PathVariable Long id) {
        return new ResponseEntity<>(resourceService.get(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Long> upload(@RequestBody MultipartFile file) {
        HttpStatus status = HttpStatus.OK;
        Long createdResourceId = null;
        try{
            createdResourceId = resourceService.upload(file);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(createdResourceId, status);
    }

    @DeleteMapping("/{ids}")
    List<Long> delete(@PathVariable List<Long> ids) {

        return resourceService.delete(ids);
    }
}
