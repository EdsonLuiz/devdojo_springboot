package com.edson.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"api/v1/customers", "api/v1/customers/"})
public class CustomerController {
    public static final List<String> NAMES = List.of("Edson", "Luiz", "Ribeiro", "Rodrigues");
    
    @GetMapping
    public List<String> list() {
        return NAMES;
    }

    @GetMapping("filter")
    public List<String> list(@RequestParam(required = false, name = "name") String pName) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(pName)).toList();
    }

    @GetMapping("filterlist")
    public List<String> list(@RequestParam(required = false) List<String> names) {
        return NAMES.stream().filter(names::contains).toList();
    }

    @GetMapping("{name}")
    public String getByName(@PathVariable(name = "name") String pName) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(pName)).findFirst().orElseGet(() -> "");
    }
}
