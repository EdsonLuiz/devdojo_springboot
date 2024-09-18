package com.edson.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Anime {
    private Long id;
    private String name;

    public static List<Anime> getAnimes() {
        return List.of(
            new Anime(1L, "Anime 01"),
            new Anime(2L, "Anime 02"),
            new Anime(3L, "Anime 03"),
            new Anime(4L, "Anime 04"),
            new Anime(5L, "Anime 05")
        );
    }
}
