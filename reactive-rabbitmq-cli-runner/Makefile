.PHONY: help dev-dependencies dev-dependencies-down dev

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

dev-dependencies: ## Runs the backend development dependencies
	docker-compose up -d

dev: ## Runs the backend service in development mode
	mvn spring-boot:run

dev-dependencies-down: ## Stops all the development dependencies
	docker-compose down
