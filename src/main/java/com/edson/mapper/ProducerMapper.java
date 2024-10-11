package com.edson.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.edson.domain.Producer;
import com.edson.request.ProducerPostRequest;
import com.edson.request.producer.ProducerPutRequest;
import com.edson.response.ProducerPostResponse;
import com.edson.response.producer.ProducerGetResponse;

@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);
    
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest request);
    
    ProducerPostResponse toProducerPostResponse(Producer producer);
    List<ProducerGetResponse> toProducerGetResponse(List<Producer> producers);

    @Mapping(source = "createdAt", target = "createdAt")
    Producer toProducer(ProducerPutRequest request, LocalDateTime createdAt);
}
