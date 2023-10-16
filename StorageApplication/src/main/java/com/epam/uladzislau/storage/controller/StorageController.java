package com.epam.uladzislau.storage.controller;

import java.util.List;

import com.epam.uladzislau.storage.dto.StorageDto;
import com.epam.uladzislau.storage.model.Storage;
import com.epam.uladzislau.storage.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/all/{page}")
    Page<Storage> getAllPage(@PathVariable int page) {
        return storageService.getAllPage(page);
    }

    @GetMapping("/all")
    List<Storage> getAll() {
        return storageService.getAll();
    }

    @PostMapping
    int create(@RequestBody StorageDto storageDto) {
        return storageService.create(storageDto.storageType, storageDto.bucket, storageDto.path);
    }

    @DeleteMapping("/{ids}")
    List<Long> delete(@PathVariable List<Long> ids) {

        return storageService.delete(ids);
    }
}
