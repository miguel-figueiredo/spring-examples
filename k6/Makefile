.PHONY: help dev-dependencies dev-dependencies-down dev performance-test

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

dev-dependencies: ## Runs the backend development dependencies
	docker-compose up -d postgres influxdb grafana

dev: ## Runs the backend service in development mode
	mvn spring-boot:run

dev-dependencies-down: ## Stops all the development dependencies
	docker-compose down

performance-test: ## Runs performance tests
	k6 run --out influxdb=http://localhost:8086/k6 --system-tags proto,subproto,status,method,url,name,group,check,error,tls_version,scenario,service,rpc_type,vu src/test/k6/test_get.js
