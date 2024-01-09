package com.example.jpa.collections.repository;

import com.example.jpa.collections.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<House, UUID> {
}
