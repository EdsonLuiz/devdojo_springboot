package com.edson.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edson.domain.Anime;

@RestController
@RequestMapping(path = {"api/v1/animes", "api/v1/animes/"})
public class AnimeController {
    @GetMapping()
    public List<Anime> list(@RequestParam(required = false, name = "name") String pName) {
        if (pName == null || pName.isBlank()) {
            return Anime.getAnimes();
        }
        return Anime.getAnimes().stream().filter(name -> name.getName().equalsIgnoreCase(pName)).toList();
    }

    @GetMapping("{id}")
    public Anime getById(@PathVariable(name = "id") Long pId) {
        return Anime.getAnimes().stream().filter(anime -> anime.getId().equals(pId)).findFirst().orElse(null);
    } 
}
