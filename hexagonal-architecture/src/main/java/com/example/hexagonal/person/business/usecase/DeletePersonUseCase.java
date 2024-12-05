package com.example.hexagonal.person.business.usecase;

import com.example.hexagonal.person.port.in.DeletePerson;
import com.example.hexagonal.person.port.out.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonUseCase implements DeletePerson {

    private final PersonRepository personRepository;

    public DeletePersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void execute(final Long personId) {
        personRepository.delete(personId);
    }
}
