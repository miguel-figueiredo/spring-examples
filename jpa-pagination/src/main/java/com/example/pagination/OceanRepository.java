package com.example.pagination;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OceanRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Ocean> findAll() {
        final List<OceanFishPair> resultList = entityManager.createQuery("select new com.example.pagination.OceanFishPair(ocean, fish) " +
                                "from Ocean ocean " +
                                "left join Fish fish on ocean.id = fish.oceanId",
                        OceanFishPair.class)
                .getResultList();

        return resultList.stream().collect(Collectors.groupingBy(OceanFishPair::getOcean))
                .entrySet().stream()
                .map(entry -> entry.getKey()
                        .toBuilder()
                        .fishList(entry.getValue().stream().map(OceanFishPair::getFish).toList())
                        .build())
                .toList();
    }


}
