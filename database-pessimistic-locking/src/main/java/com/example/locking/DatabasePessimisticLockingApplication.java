package com.example.locking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabasePessimisticLockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabasePessimisticLockingApplication.class, args);
	}

}
