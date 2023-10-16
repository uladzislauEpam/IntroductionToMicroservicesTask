package com.epam.uladzislau.resource.conf;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class ExternalAPICaller {
    private final RestTemplate restTemplate;

//    @Bean
    public RestTemplate restTemplate() {
//        return new RestTemplateBuilder().rootUri("http://localhost:8081")
        return new RestTemplateBuilder().rootUri("http://app-st:8081")
            .build();
    }

    public ExternalAPICaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}