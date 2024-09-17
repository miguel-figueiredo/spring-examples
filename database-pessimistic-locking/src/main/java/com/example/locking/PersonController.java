package com.example.locking;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    List<Person> list() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    Person get(@PathVariable Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    Long create() {
        Person person = new Person(RandomStringUtils.random(10));
        return personRepository.save(person).getId();
    }

    @Transactional
    @PutMapping("/{id}")
    void update(@PathVariable Long id) {
        personRepository.findById(id)
                .ifPresent(person -> person.setName(RandomStringUtils.random(10)));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
