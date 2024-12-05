package com.example.hexagonal.person.domain.usecase;

import com.example.hexagonal.person.domain.model.Person;
import com.example.hexagonal.person.port.in.UpdatePerson;
import com.example.hexagonal.person.port.out.PersonRepository;
import com.example.hexagonal.util.UseCase;

@UseCase
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
