package com.example.hexagonal.person.business.usecase;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.UpdatePerson;
import com.example.hexagonal.person.port.out.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonUseCase implements UpdatePerson {

    private final PersonRepository personRepository;

    public UpdatePersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void execute(final Person person) {
        personRepository.update(person);
    }
}
