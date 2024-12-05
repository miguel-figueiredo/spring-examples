package com.example.hexagonal.person.adapter.rest;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.SavePerson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class SavePersonController {

    private final SavePerson savePerson;

    public SavePersonController(SavePerson savePerson) {
        this.savePerson = savePerson;
    }

    @PostMapping
    public void addName(@RequestBody Person person) {
        savePerson.execute(person);
    }
}
