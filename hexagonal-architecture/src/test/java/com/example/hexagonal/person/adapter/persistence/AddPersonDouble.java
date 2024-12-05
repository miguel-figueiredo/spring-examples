package com.example.hexagonal.person.adapter.persistence;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.AddPerson;
import org.springframework.stereotype.Service;

@Service
public class AddPersonDouble implements AddPerson {
    @Override
    public void execute(final Person name) {

    }
}
