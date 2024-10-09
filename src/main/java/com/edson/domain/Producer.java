package com.edson.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Producer {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {
        var producer01 = Producer.builder().id(1L).name("Producer01").createdAt(LocalDateTime.now()).build();
        var producer02 = Producer.builder().id(2L).name("Producer02").createdAt(LocalDateTime.now()).build();;
        var producer03 = Producer.builder().id(3L).name("Producer03").createdAt(LocalDateTime.now()).build();;
        var producer04 = Producer.builder().id(4L).name("Producer04").createdAt(LocalDateTime.now()).build();;
        producers.addAll(List.of(producer01, producer02, producer03, producer04));
    }

}
