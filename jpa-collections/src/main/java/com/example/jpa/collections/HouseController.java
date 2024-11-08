package com.example.jpa.collections;

import com.example.jpa.collections.model.Door;
import com.example.jpa.collections.model.House;
import com.example.jpa.collections.repository.HouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @PostMapping
    @Transactional
    public void createHouse() {
        final Door door = new Door(UUID.randomUUID());
        final House house = new House(UUID.randomUUID(), List.of(door));
        houseRepository.save(house);
    }

}
