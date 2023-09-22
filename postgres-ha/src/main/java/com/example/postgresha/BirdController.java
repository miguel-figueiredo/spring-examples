package com.example.postgresha;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/birds")
public class BirdController {

    private BirdRepository repository;

    public BirdController(BirdRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Bird> get() {
        return repository.findAll();
    }

    @PostMapping
    public Bird save(@RequestBody Bird bird) {
        return repository.save(bird);
    }
}
