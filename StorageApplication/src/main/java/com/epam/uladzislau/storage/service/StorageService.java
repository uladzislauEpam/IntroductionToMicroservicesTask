package com.epam.uladzislau.storage.service;

import java.util.List;

import com.epam.uladzislau.storage.model.Storage;
import com.epam.uladzislau.storage.repository.StorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    StorageRepository storageRepository;

    public Page<Storage> getAllPage(int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return storageRepository.findAll(pageRequest);
    }

    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

    public int create(String storageType, String bucket, String path) {
        Storage storage = storageRepository.save(new Storage(storageType, bucket, path));
        return storage.getId().intValue();
    }

    public List<Long> delete(List<Long> ids) {
        for(Long id : ids) {
            storageRepository.deleteById(id);
        }
        return ids;
    }

}
