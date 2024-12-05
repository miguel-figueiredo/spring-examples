package com.example.hexagonal.person.adapter.in.rest;

import com.example.hexagonal.person.domain.model.Person;
import com.example.hexagonal.person.port.in.ListPersons;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class ListPersonsController {

    private final ListPersons listPersons;

    public ListPersonsController(ListPersons getPersons) {
        this.listPersons = getPersons;
    }

    @GetMapping
    public List<Person> getPersons() {
        return listPersons.execute();
    }


}
