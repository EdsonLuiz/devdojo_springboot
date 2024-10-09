package com.edson.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edson.domain.Producer;
import com.edson.mapper.ProducerMapper;
import com.edson.request.ProducerPostRequest;
import com.edson.response.ProducerPostResponse;
import com.edson.response.producer.ProducerGetResponse;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = {"api/v1/producers", "api/v1/producers/"})
@Log4j2
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping()
    public ResponseEntity<List<ProducerGetResponse>> list(@RequestParam(required = false, name = "name") String pName) {
        log.info("Request received to list all producers, param name '{}'", pName);
        var producers = Producer.getProducers();
        var response = MAPPER.toProducerGetResponse(producers);
        if (pName == null || pName.isBlank()) {
            return ResponseEntity.ok().body(response);
        }
        response = response.stream().filter(producer -> producer.getName().equalsIgnoreCase(pName)).toList();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-version=v1")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest pProducer) {

        var producer = MAPPER.toProducer(pProducer);
        producer.setId(ThreadLocalRandom.current().nextLong(100_000));

        Producer.getProducers().add(producer);
        var resProducer = MAPPER.toProducerPostResponse(producer);
        return ResponseEntity.status(HttpStatus.CREATED).body(resProducer);
    }
}
