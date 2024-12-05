package com.example.hexagonal.person.business.usecase;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.SavePerson;
import com.example.hexagonal.person.port.out.PersonRepository;
import com.example.hexagonal.util.UseCase;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@UseCase
public class SavePersonUseCase implements SavePerson {

    private final AtomicLong sequence = new AtomicLong(0);
    private final PersonRepository personRepository;

    public SavePersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void execute(final NewPerson person) {
        personRepository.save(toPerson(person));
    }

    private Person toPerson(final NewPerson person) {
        return new Person(
                sequence.incrementAndGet(),
                person.firstName(),
                person.lastName());
    }
}
