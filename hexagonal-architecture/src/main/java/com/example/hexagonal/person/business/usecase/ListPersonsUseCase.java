package com.example.hexagonal.person.business.usecase;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.ListPersons;
import com.example.hexagonal.person.port.out.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPersonsUseCase implements ListPersons {

    private final PersonRepository personRepository;

    public ListPersonsUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> execute() {
        return personRepository.list();
    }

}
