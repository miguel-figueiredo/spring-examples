package com.example.pagination;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OceanRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Ocean> findAll() {
        return entityManager.createQuery("from Ocean", Ocean.class)
                .setMaxResults(1)
                .getResultList();
    }
}
