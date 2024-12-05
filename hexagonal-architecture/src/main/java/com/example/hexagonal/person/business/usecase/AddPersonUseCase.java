package com.example.hexagonal.person.business.usecase;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.AddPerson;
import org.springframework.stereotype.Service;

// TODO: replace with alias @UseCase
@Service
public class AddPersonUseCase implements AddPerson {
    @Override
    public void execute(final Person person) {
        System.out.println("Person added: " + person);
    }
}
