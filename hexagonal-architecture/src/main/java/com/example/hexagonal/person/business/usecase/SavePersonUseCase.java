package com.example.hexagonal.person.business.usecase;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.SavePerson;
import com.example.hexagonal.person.port.out.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

// TODO: replace with alias @UseCase
@Service
public class SavePersonUseCase implements SavePerson {

    private final AtomicLong sequence = new AtomicLong(0);
    private final PersonRepository personRepository;

    public SavePersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void execute(final Person person) {
        personRepository.save(person.withId(sequence.incrementAndGet()));
    }
}
