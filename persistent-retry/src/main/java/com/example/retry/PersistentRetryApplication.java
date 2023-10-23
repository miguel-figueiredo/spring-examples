package com.example.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.policy.RetryContextCache;
import org.springframework.retry.support.RetryTemplate;

@EnableRetry
@SpringBootApplication
public class PersistentRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistentRetryApplication.class, args);
	}

	@Bean
	public RetryTemplate retryTemplate(RetryContextCache contextCache) {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryContextCache(contextCache);
		return retryTemplate;
	}

	@Bean
	public RetryContextCache retryContextCache() {
		return new RedisRetryContextCache();
	}

}
