package com.example.pagination;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
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

        return resultList.stream().collect(Collectors.groupingBy(OceanFishPair::getOcean, TreeMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(OceanRepository::toOcean)
                .toList();
    }

    private static Ocean toOcean(final Map.Entry<Ocean, List<OceanFishPair>> entry) {
        return entry.getKey()
                .toBuilder()
                .fishList(toFishList(entry))
                .build();
    }

    private static List<Fish> toFishList(final Map.Entry<Ocean, List<OceanFishPair>> entry) {
        return entry.getValue().stream()
                .map(OceanFishPair::getFish)
                .filter(Objects::nonNull)
                .toList();
    }


}
