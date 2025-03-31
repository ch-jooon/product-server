## 목차

- [📍프로젝트 개요](#프로젝트-개요)
    - [사용 기술](#사용-기술)
- [🚀실행 방법](#실행-방법)
- [🛠️시스템 아키텍처](#시스템-아키텍처)
    - [전체 구조](#전체-구조)
    - [모듈 구성](#모듈-구성)
    - [테이블 스키마](#테이블-스키마)
    - [주요 기능 및 구현 방식](#주요-기능-및-구현-방식)
- [📝API 명세](#api-명세)

## 프로젝트 개요

### 기능 설명

Kotlin + SpringBoot 기반의 상품을 관리 및 전시하는 기능을 가진 API 서버입니다.

다음 기능을 제공합니다.

유저
1. 카테고리 별 최저가격 상품 카탈로그 조회
2. 모든 카테고리를 포함하는 최저가격 브랜드 상품 카탈로그 조회
3. 카테고리 별 최저, 최고 가격 상품 카탈로그 조회

관리자
1. 카테고리 전체 조회
2. 브랜드 전체 조회
3. 브랜드 추가
4. 브랜드 수정
5. 브랜드 삭제
6. 상품 추가
7. 상품 수정
8. 상품 삭제

### 사용 기술

- **언어**
    - `Java 21`
    - `Kotlin 1.9.25`
    - `Gradle 8.7`
- **프레임워크**
    - `Spring Boot 3.4.2`
- **Web**
    - `Spring Web MVC`
- **DB**
    - `Spring Data JPA`
    - `H2 Database`
    - `kotlin-jdsl`
- **테스트**
    - `JUnit 5`
    - `Kotest`
    - `Rest Assured`

## 실행 방법

### 사전 준비
> 해당 API 서버는 `Java 21` 이상에서 실행됩니다.
>
> 다음 명령어를 통해 로컬 환경의 Java 버전을 확인합니다.
>
> ```shell
> java -version
> ```

해당 서버는 `8080` 포트를 사용합니다.

포트 충돌을 방지하기 위해 해당 포트를 사용하지 않는지 확인해주세요.

### Gradle 실행

`gradlew` 명령어를 통해 서버를 직접 실행합니다.

```shell
./gradlew :product-api:bootRun
```

혹은 `Makefile`을 통해 실행할 수 있습니다.

```shell
make run
```

### Docker 실행

> Docker 환경으로 실행하기 위해서는 로컬 환경에 `Docker`와 `Docker Compose`가 설치되어 있어야 합니다.

`docker-compose`를 통해 서버를 실행합니다.

```shell
docker-compose up -d
```

혹은 `Makefile` 을 통해 실행할 수 있습니다.

```shell
make up
```

### 테스트

`gradlew` 명령어를 통해 전체 테스트를 실행합니다.

```shell
./gradlew clean test
```

혹은 `Makefile`을 통해 실행할 수 있습니다.

```shell
make test
```

## 시스템 아키텍처

### 전체 구조

TBD

### 모듈 구성

해당 프로젝트는 `gradle` 멀티 모듈 프로젝트로 구성되어 있습니다.

도메인을 중심 설계를 위해 각 책임 별 모듈을 구성해 의존성을 명시적으로 분리시켰습니다.

다음과 같은 모듈로 구성됩니다.

```
├── product-api
├── product-core
└── product-infra
    └── product-infra-cache
    └── product-infra-jpa
```

각 모듈은 다음과 같은 역할을 수행합니다.

- `product-api`
    - 실행 가능한 API 서버 모듈입니다. (Executable JAR)
    - 외부 요청을 받아서 비즈니스 로직을 실행시키는 책임을 가집니다.
- `product-core`
    - 핵심 비즈니스 로직을 담당하는 책임을 가집니다.
    - 도메인과 관련된 유즈케이스, 서비스를 제공합니다.
- `product-infra`
    - 도메인 개념들을 실제 인프라를 통해 구현하는 책임을 가집니다.
    - `product-infra-jpa`: 도메인 객체를 JPA 엔티티로 변환하고 영속화하는 책임을 가집니다.
    - `product-infra-cache`: 캐시를 사용하여 조회 성능을 향상시키는 책임을 가집니다. 필요에 따라 다양한 캐시 인프라를 구현할 수 있습니다. (예: Redis, Caffeine 등)

### 테이블 스키마

TBD

### 주요 기능 및 구현 방식
TBD

## API 명세

API 명세는 `SwaggerUI`를 통해 확인할 수 있습니다.

[🚀실행 방법](#실행-방법)을 통해 서버 실행 후 

http://localhost:8080/docs/swagger-ui 로 접속하여 확인할 수 있습니다.