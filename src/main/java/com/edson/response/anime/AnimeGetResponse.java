package com.edson.response.anime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AnimeGetResponse {
    private Long id;
    private String name;
}
