package com.example.hexagonal.person.port.in;

import com.example.hexagonal.person.business.model.Person;

import java.util.List;

public interface ListPersons {

    List<Person> execute();
}