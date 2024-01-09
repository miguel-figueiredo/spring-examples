package com.example.jpa.collections.repository;

import com.example.jpa.collections.PostgreSQLExtension;
import com.example.jpa.collections.model.Door;
import com.example.jpa.collections.model.House;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
class HouseRepositoryTest {

    @Autowired
    HouseRepository subject;

    @Test
    void name() {
        final Door door = new Door(UUID.randomUUID());
        final House house = new House(UUID.randomUUID(), List.of(door));

        subject.save(house);
    }
}