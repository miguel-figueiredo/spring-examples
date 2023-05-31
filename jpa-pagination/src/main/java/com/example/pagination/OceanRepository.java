package com.example.pagination;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OceanRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Ocean> findAll() {
        final List<Ocean> oceans = entityManager.createQuery("from Ocean", Ocean.class)
                .getResultList();
        final Map<UUID, List<Fish>> fishMap = entityManager.createQuery("from Fish where oceanId in (:oceanIds)", Fish.class)
                .setParameter("oceanIds", oceans.stream().map(Ocean::getId).toList())
                .getResultList()
                .stream().collect(Collectors.groupingBy(Fish::getOceanId));

        return oceans.stream().map(ocean -> ocean.toBuilder().fishList(fishMap.get(ocean.getId())).build()).toList();
    }
}
