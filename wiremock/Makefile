.PHONY: run

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

run: ## Builds the extension and runs Wiremock with Docker
	mvn clean install
	docker run -it --rm -p 8080:8080 --name wiremock -v `pwd`/target:/var/wiremock/extensions -v `pwd`/src/main/resources:/home/wiremock wiremock/wiremock --verbose --extensions com.example.wiremock.MyServeEventListener

request: ## Sends the HTTP request to Wiremock
	curl http://localhost:8080/hello
