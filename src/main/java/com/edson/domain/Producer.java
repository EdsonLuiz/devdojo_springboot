package com.edson.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Producer {
    private Long id;
    private String name;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {
        var producer01 = new Producer(01L, "Producer01");
        var producer02 = new Producer(01L, "Producer02");
        var producer03 = new Producer(01L, "Producer03");
        var producer04 = new Producer(01L, "Producer04");
        producers.addAll(List.of(producer01, producer02, producer03, producer04));
    }

}
