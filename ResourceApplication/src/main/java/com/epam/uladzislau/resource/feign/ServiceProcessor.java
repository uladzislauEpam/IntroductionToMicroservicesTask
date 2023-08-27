package com.epam.uladzislau.resource.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("ResourceProcessor")
public interface ServiceProcessor {

    @DeleteMapping("api/processor/{id}")
    void deleteSong(@PathVariable("id") long id);

}