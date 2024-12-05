package com.example.hexagonal.person.adapter.out.persistence;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.out.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;

@Service
public class PersonRepositoryInMemory implements PersonRepository {

    private final Map<Long, Person> persons = new HashMap<>();

    @Override
    public void save(final Person person) {
        persons.put(person.id(), person);
    }

    @Override
    public List<Person> list() {
        return persons.values().stream()
                .sorted(comparing(Person::id))
                .toList();
    }

    @Override
    public void update(final Person person) {
        final Person persisted = persons.get(person.id());
        if(persisted != null) {
            persons.put(person.id(), person);
        }
    }

    @Override
    public void delete(final Long personId) {
        persons.remove(personId);
    }
}
