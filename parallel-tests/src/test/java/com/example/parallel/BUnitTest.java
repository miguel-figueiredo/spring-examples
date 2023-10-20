package com.example.parallel;

import org.junit.jupiter.api.Test;

public class BUnitTest {

    @Test
    void testA() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Test
    void testB() throws InterruptedException {
        Thread.sleep(5000);
    }
}
