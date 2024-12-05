package com.example.hexagonal.name.adapter.rest;

import com.example.hexagonal.name.port.in.AddName;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/names")
public class AddNameController {

    private final AddName addName;

    public AddNameController(AddName addName) {
        this.addName = addName;
    }

    @PostMapping
    public void addName(@RequestBody String name) {
        addName.execute(name);
    }
}
