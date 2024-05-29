package com.example.configuration.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ConfigurationValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationValidationApplication.class, args);
	}

}
