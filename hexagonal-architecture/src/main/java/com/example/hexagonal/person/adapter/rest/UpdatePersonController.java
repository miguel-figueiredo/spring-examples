package com.example.hexagonal.person.adapter.rest;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.SavePerson;
import com.example.hexagonal.person.port.in.UpdatePerson;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class UpdatePersonController {

    private final UpdatePerson updatePerson;

    public UpdatePersonController(UpdatePerson updatePerson) {
        this.updatePerson = updatePerson;
    }

    @PutMapping
    public void addName(@RequestBody Person person) {
        updatePerson.execute(person);
    }
}
