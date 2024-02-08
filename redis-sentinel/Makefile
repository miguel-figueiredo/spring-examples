.PHONY: run

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

run: ## Runs the whole shebang
	mvn spring-boot:build-image
	docker-compose up -d
	docker logs -f app

