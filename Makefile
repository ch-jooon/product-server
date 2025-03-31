test:
	./gradlew clean test
.PHONY: test

build:
	./gradlew clean build
.PHONY: build

up:
	docker-compose up -d
.PHONY: up

down:
	docker-compose down
.PHONY: down

run:
	./gradlew :product-api:bootRun
.PHONY: run