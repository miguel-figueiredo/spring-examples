package com.test.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessingApplication {

	public static void main(String[] args) {
		System.setProperty("reactor.bufferSize.small", "10");
		SpringApplication.run(ProcessingApplication.class, args);
	}

}
