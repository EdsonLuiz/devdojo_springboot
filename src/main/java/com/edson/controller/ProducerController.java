package com.edson.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edson.domain.Producer;
import com.edson.mapper.ProducerMapper;
import com.edson.request.ProducerPostRequest;
import com.edson.response.ProducerPostResponse;

@RestController
@RequestMapping(path = {"api/v1/producers", "api/v1/producers/"})
public class ProducerController {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-version=v1")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest pProducer) {

        var mapper = ProducerMapper.INSTANCE;
        var producer = mapper.toProducer(pProducer);
        producer.setId(ThreadLocalRandom.current().nextLong(100_000));

        Producer.getProducers().add(producer);
        var resProducer = mapper.toProducerPostResponse(producer);
        return ResponseEntity.status(HttpStatus.CREATED).body(resProducer);
    }
}
