package com.edson.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"api/v1/animes", "api/v1/animes/"})
public class AnimeController {
    @GetMapping()
    public List<String> list() {
        return List.of("Anime 01", "Anime 02", "Anime 03", "Anime 04", "Anime 05");
    }
}
