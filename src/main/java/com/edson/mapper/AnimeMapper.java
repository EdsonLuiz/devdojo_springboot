package com.edson.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.edson.domain.Anime;
import com.edson.request.anime.AnimePostRequest;
import com.edson.response.AnimePostResponse;
import com.edson.response.anime.AnimeGetResponse;

@Mapper
public interface AnimeMapper{
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest request);

    AnimePostResponse toAnimePostResponse(Anime anime);
    AnimeGetResponse toAnimeGetResponse(Anime anime);
    List<AnimeGetResponse> toAnimeGetResponse(List<Anime> animes);
}
