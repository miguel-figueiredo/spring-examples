.PHONY: help dev-dependencies dev-dependencies-down dev send-message upload-file purge-queue

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

dev-dependencies: ## Runs the backend development dependencies
	docker-compose up -d

dev: ## Runs the backend service in development mode
	mvn spring-boot:run

debug: ## Runs the backend service in debug mode
	mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7777"

dev-dependencies-down: ## Stops all the development dependencies
	docker-compose down

send-message:
	docker exec localstack awslocal sqs send-message --queue-url http://localhost:4566/000000000000/test-queue --message-body test

upload-file:
	file=file-$$(date +"%Y-%m-%dT%H:%M:%S") && \
	docker exec -it localstack touch /tmp/$${file} && \
	docker exec localstack awslocal s3 cp /tmp/$${file} s3://test-bucket/$${file}

purge-queue:
	docker exec localstack awslocal sqs purge-queue --queue-url http://localhost:4566/000000000000/test-queue
