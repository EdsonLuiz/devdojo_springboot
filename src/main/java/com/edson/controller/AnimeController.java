package com.edson.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edson.domain.Anime;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = {"api/v1/animes", "api/v1/animes/"})
@Log4j2
public class AnimeController {
    @GetMapping()
    public List<Anime> list(@RequestParam(required = false, name = "name") String pName) {
        log.info("Request received to list all animes, param name '{}'", pName);
        if (pName == null || pName.isBlank()) {
            return Anime.getAnimes();
        }
        return Anime.getAnimes().stream().filter(name -> name.getName().equalsIgnoreCase(pName)).toList();
    }

    @GetMapping("{id}")
    public Anime getById(@PathVariable(name = "id") Long pId) {
        log.info("Request received to find anime by id '{}'", pId);
        return Anime.getAnimes().stream().filter(anime -> anime.getId().equals(pId)).findFirst().orElse(null);
    }

    @PostMapping
    public Anime save(@RequestBody Anime pAnime) {
        pAnime.setId(ThreadLocalRandom.current().nextLong(100_000));
        log.info("Saving anime id '{}'", pAnime.getId());
        Anime.getAnimes().add(pAnime);
        return pAnime;
    }
}
