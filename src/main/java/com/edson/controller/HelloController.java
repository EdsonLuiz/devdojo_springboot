package com.edson.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("greetings")
@Log4j2
public class HelloController {
    @GetMapping("hi")
    public String hi() {
        return "hi";
    }

    @PostMapping
    public Long save(@RequestBody String pName) {
        log.info("Saving name '{}'", pName);
        return ThreadLocalRandom.current().nextLong();
    }
}
