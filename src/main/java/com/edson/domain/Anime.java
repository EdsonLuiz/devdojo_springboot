package com.edson.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    @Getter
    private static List<Anime> animes = new ArrayList<>();

    static {
        var anime01 = Anime.builder().id(1L).name("Anime 01").build();
        var anime02 = Anime.builder().id(2L).name("Anime 02").build();
        var anime03 = Anime.builder().id(3L).name("Anime 03").build();
        var anime04 = Anime.builder().id(4L).name("Anime 04").build();
        var anime05 = Anime.builder().id(5L).name("Anime 05").build();
        animes.addAll(List.of(
                anime01,
                anime02,
                anime03,
                anime04,
                anime05));
    }
}
