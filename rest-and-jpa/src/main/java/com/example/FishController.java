package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fishes")
@Slf4j
public class FishController {

    @Autowired
    FishRepository repository;

    @Autowired
    FishApiClient webClient;

    @PostMapping
    Fish addFish(@RequestBody Fish fish) {
        return repository.save(fish);
    }

    @GetMapping("/{id}")
    String getFish(@PathVariable String id) {
        return webClient.get(id);
    }
}
