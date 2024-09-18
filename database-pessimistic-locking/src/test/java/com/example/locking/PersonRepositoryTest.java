package com.example.locking;

import org.apache.commons.lang3.function.Failable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
class PersonRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres");

    @Autowired
    PersonRepository subject;

    @Autowired
    TransactionTemplate transactionTemplate;

    ExecutorService executor = Executors.newFixedThreadPool(2);

    @Test
    void concurrentQueryFails() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final Person person = new Person("name", List.of(new Address("address")));
        transactionTemplate.executeWithoutResult(status -> subject.save(person));
        final Future<Optional<Person>> result1 = executor.submit(() -> {
            Failable.asRunnable(latch::await);
            return transactionTemplate.execute(status -> subject.findById(1L));
        });
        final Future<Optional<Person>> result2 = executor.submit(() -> {
            Failable.asRunnable(latch::await);
            return transactionTemplate.execute(status -> subject.findById(1L));
        });

        latch.countDown();

        assertThrows(ExecutionException.class, () -> {
            result1.get();
            result2.get();
        });
    }
}