package com.epam.uladzislau.resource.feign;

import com.epam.uladzislau.resource.dto.StorageDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("StorageApplication")
public interface StorageProcessor {
    @PostMapping("api/storage")
    int create(@RequestBody StorageDto storageDto);
}
