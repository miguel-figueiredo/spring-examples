package com.example.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oceans")
public class OceanController {

    @Autowired
    private OceanRepository repository;

    @GetMapping
    public List<Ocean> hello() {
        return repository.findAll();
    }
}
