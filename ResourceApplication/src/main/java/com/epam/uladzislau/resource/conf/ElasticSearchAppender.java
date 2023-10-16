package com.epam.uladzislau.resource.conf;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.reactive.function.client.WebClient;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import reactor.core.publisher.Mono;

public class ElasticSearchAppender extends AppenderBase<ILoggingEvent> {
    private static final String ELASTIC_SEARCH_API_HOST = "http://localhost:9200";
    private static final String ELASTIC_SEARCH_INDEX_NAME = "index";
    private static final WebClient webClient = WebClient.create(ELASTIC_SEARCH_API_HOST);
    private static final Logger LOGGER = Logger.getLogger(ElasticSearchAppender.class.getName());
    public static final DateTimeFormatter ISO_8601_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            .withZone(ZoneId.systemDefault());

    @Override
    protected void append(ILoggingEvent eventObject) {
        Map<String, Object> loggingEvent = new LinkedHashMap<>();
        loggingEvent.put("@timestamp", 
                ISO_8601_FORMAT.format(Instant.ofEpochMilli(eventObject.getTimeStamp())));
        loggingEvent.put("message", eventObject.getMessage());
        
        webClient.post()
            .uri("/{logIndex}/_doc", ELASTIC_SEARCH_INDEX_NAME)
            .bodyValue(loggingEvent)
            .retrieve()
            .bodyToMono(Void.class)
            .onErrorResume(exception -> {
                LOGGER.log(Level.SEVERE, "Unable to send log to elastic", exception);
                return Mono.empty();
            })
            .subscribe();
    }

}