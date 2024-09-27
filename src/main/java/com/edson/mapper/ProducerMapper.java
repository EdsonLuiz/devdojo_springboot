package com.edson.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.edson.domain.Producer;
import com.edson.request.ProducerPostRequest;
import com.edson.response.ProducerPostResponse;

@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Producer toProducer(ProducerPostRequest request);
    ProducerPostResponse toProducerPostResponse(Producer producer);
}
