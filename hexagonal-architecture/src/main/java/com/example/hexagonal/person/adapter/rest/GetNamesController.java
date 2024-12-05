package com.example.hexagonal.person.adapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class GetNamesController {

    @GetMapping
    public List<String> getPersons() {
        return List.of("Alice", "Bob", "Charlie");
    }


}
