package com.example;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
class FishRepositoryTest {

    @Autowired
    FishRepository subject;

    @Autowired
    EntityManager entityManager;

    @Test
    void changePrey() {
        final Fish fish = new Fish();
        fish.setPreyList(List.of(new Prey()));

        subject.save(fish);

        entityManager.detach(fish);

        System.err.println("------------------------");

        fish.setPreyList(List.of(new Prey()));

        subject.save(fish);
    }

    @Test
    void renamePey() {
        final Fish fish = new Fish();
        final Prey prey = new Prey("name");
        fish.setPreyList(List.of(prey));

        subject.save(fish);

        entityManager.detach(fish);

        prey.setName("new_name");
        fish.setPreyList(List.of(prey));

        subject.save(fish);
    }

}