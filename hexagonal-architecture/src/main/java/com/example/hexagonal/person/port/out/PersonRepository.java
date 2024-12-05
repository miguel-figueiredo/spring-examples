package com.example.hexagonal.person.port.out;

import com.example.hexagonal.person.business.model.Person;

import java.util.List;

public interface PersonRepository {

    void save(Person person);

    List<Person> list();

    void update(Person person);

    void delete(Long personId);
}
