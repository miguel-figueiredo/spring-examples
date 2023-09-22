package com.example.postgresha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BirdRepository extends JpaRepository<Bird, UUID> {
}
