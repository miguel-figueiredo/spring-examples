package com.example.hexagonal.person.adapter.rest;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.AddPerson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class AddPersonController {

    private final AddPerson addName;

    public AddPersonController(AddPerson addName) {
        this.addName = addName;
    }

    @PostMapping
    public void addName(@RequestBody Person person) {
        addName.execute(person);
    }
}
