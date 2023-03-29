.PHONY: help dev-dependencies dev-dependencies-down dev

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

dev-dependencies: ## Runs the backend development dependencies
	docker-compose up -d postgres

dev: ## Runs the backend service in development mode
	mvn spring-boot:run

dev-dependencies-down: ## Stops all the development dependencies
	docker-compose down

performance-test-create: ## Runs create performance tests
	k6 run src/main/k6/test_create.js

performance-test-get: ## Runs get performance tests
	k6 run src/main/k6/test_get.js

docker: ## Builds the docker image
	mvn spring-boot:build-image -DskipTests

run: dev-dependencies ## Run the application and dependencies
	docker-compose up -d