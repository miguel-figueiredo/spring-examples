package com.example.locking;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "0"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("from Person person left join fetch person.addresses where person.id = :id")
    Optional<Person> findById(Long id);

    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "0"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void deleteById(Long id);
}
