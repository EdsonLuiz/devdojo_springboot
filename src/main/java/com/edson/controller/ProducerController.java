package com.edson.controller;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edson.domain.Producer;
import com.edson.request.ProducerPostRequest;
import com.edson.response.ProducerPostResponse;

@RestController
@RequestMapping(path = {"api/v1/producers", "api/v1/producers/"})
public class ProducerController {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-version=v1")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest pProducer) {
        var producer = Producer.builder()
            .id(ThreadLocalRandom.current().nextLong(100_000))
            .createdAt(LocalDateTime.now())
            .name(pProducer.getName()).build();
        Producer.getProducers().add(producer);
        var resProducer = ProducerPostResponse.builder()
            .id(producer.getId())
            .name(producer.getName())
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(resProducer);
    }
}
