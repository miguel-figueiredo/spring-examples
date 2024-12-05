package com.example.hexagonal.person.adapter.rest;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.DeletePerson;
import com.example.hexagonal.person.port.in.SavePerson;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class DeletePersonController {

    private final DeletePerson deletePerson;

    public DeletePersonController(final DeletePerson deletePerson) {
        this.deletePerson = deletePerson;
    }

    @DeleteMapping("/{personId}")
    public void delete(@PathVariable Long personId) {
        deletePerson.execute(personId);
    }
}
