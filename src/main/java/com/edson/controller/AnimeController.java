package com.edson.controller;

import java.util.List;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.edson.domain.Anime;
import com.edson.mapper.AnimeMapper;
import com.edson.request.anime.AnimePostRequest;
import com.edson.response.AnimePostResponse;
import com.edson.response.anime.AnimeGetResponse;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = {"api/v1/animes", "api/v1/animes/"})
@Log4j2
public class AnimeController {

    private static AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping()
    public ResponseEntity<List<AnimeGetResponse>> list(@RequestParam(required = false, name = "name") String pName) {
        log.info("Request received to list all animes, param name '{}'", pName);
        List<Anime> animes = Anime.getAnimes();
        var response = MAPPER.toAnimeGetResponse(animes);;
        if (pName == null || pName.isBlank()) {
            return ResponseEntity.ok().body(response);
        }
        response = response.stream().filter(anime -> anime.getName().equalsIgnoreCase(pName)).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> getById(@PathVariable(name = "id") Long pId) {
        log.info("Request received to find anime by id '{}'", pId);
        var animeFound = Anime.getAnimes().stream().filter(anime -> anime.getId().equals(pId)).findFirst().orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Anime not found."));
        var response = MAPPER.toAnimeGetResponse(animeFound);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        // log.info("Saving anime '{}'", request);
        var anime = MAPPER.toAnime(request);
        Anime.getAnimes().add(anime);
        var response = MAPPER.toAnimePostResponse(anime);
        return ResponseEntity.status(CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Request received to delete anime with id '{}'", id);
        var anime = Anime.getAnimes().stream().filter(a -> a.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Anime not found to be deleted."));
        Anime.getAnimes().remove(anime);
        return ResponseEntity.noContent().build();
    }
}
