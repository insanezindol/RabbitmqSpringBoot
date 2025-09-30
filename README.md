# RabbitMQ Spring Boot 샘플 프로젝트

Spring Boot와 RabbitMQ를 사용한 메시지 큐 시스템 샘플 프로젝트입니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Boot 2.3.3과 RabbitMQ를 활용하여 비동기 메시지 처리 시스템을 구현한 샘플 애플리케이션입니다. 카페 주문 시스템을 모델로 하여 메시지 발행(Producer)과 구독(Consumer) 패턴을 보여줍니다.

## 🛠 기술 스택

-   **Java**: 1.8
-   **Spring Boot**: 2.3.3.RELEASE
-   **Spring AMQP**: RabbitMQ 메시지 처리
-   **RabbitMQ**: 메시지 브로커
-   **Lombok**: 코드 간소화
-   **Maven**: 빌드 도구

## 📁 프로젝트 구조

```
src/main/java/com/rabbitmq/sample/
├── SampleApplication.java          # Spring Boot 메인 애플리케이션
├── config/
│   └── RabbitConfig.java          # RabbitMQ 설정
├── controller/
│   └── RabbitController.java      # 메시지 발행 REST API
├── message/
│   └── CafeMessageListener.java   # 메시지 리스너 (Consumer)
└── service/
    └── RabbitService.java         # 비동기 서비스
```

## ⚙️ RabbitMQ 설정

### Queue 설정

-   **Exchange**: `cafe.topic` (Topic Exchange)
-   **Queue**: `coffee.queue`
-   **Routing Key**: `order.coffee.#`

### 연결 설정 (application.yml)

```yaml
spring:
    rabbitmq:
        host: localhost
        port: 5672
        username: rabbitmq
        password: 1234
```

## 🚀 실행 방법

### 1. RabbitMQ 서버 설치 및 실행

#### Docker를 사용하는 경우:

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=rabbitmq \
  -e RABBITMQ_DEFAULT_PASS=1234 \
  rabbitmq:3-management
```

#### 로컬 설치:

-   [RabbitMQ 공식 사이트](https://www.rabbitmq.com/download.html)에서 다운로드
-   사용자명: `rabbitmq`, 비밀번호: `1234`로 설정

### 2. 애플리케이션 실행

```bash
# Maven을 사용한 실행
./mvnw spring-boot:run

# 또는 JAR 파일 빌드 후 실행
./mvnw clean package
java -jar target/sample-1.0.jar
```

애플리케이션은 `http://localhost:8081`에서 실행됩니다.

## 📡 API 엔드포인트

### 1. 문자열 메시지 발행

```http
GET http://localhost:8081/rabbit/pub/str
```

-   간단한 문자열 메시지를 `order.coffee.string` 라우팅 키로 발행

### 2. JSON 메시지 발행 (MessageBuilder 사용)

```http
GET http://localhost:8081/rabbit/pub/builder
```

-   JSON 형태의 메시지를 `order.coffee.builder` 라우팅 키로 발행

### 3. 비동기 서비스 테스트

```http
GET http://localhost:8081/rabbit/async
```

-   비동기 서비스 실행 테스트

## 💡 주요 기능

### 메시지 발행 (Producer)

-   `RabbitController`에서 REST API를 통해 메시지 발행
-   문자열 및 JSON 메시지 지원
-   Topic Exchange 패턴 사용

### 메시지 수신 (Consumer)

-   `CafeMessageListener`에서 `@RabbitListener` 어노테이션을 사용한 메시지 수신
-   비동기 처리 (`@Async`)
-   10초 지연 처리로 비동기 동작 시뮬레이션

### 비동기 처리

-   `@EnableAsync`와 `@Async` 어노테이션을 사용한 비동기 처리
-   논블로킹 메시지 처리

## 🔍 메시지 플로우

1. **메시지 발행**: REST API 호출 → `RabbitTemplate`을 통해 메시지 발행
2. **메시지 라우팅**: Topic Exchange에서 라우팅 키 패턴 매칭
3. **메시지 수신**: Queue에서 메시지 수신 → 비동기 처리

## 📊 모니터링

RabbitMQ Management UI를 통해 메시지 상태를 모니터링할 수 있습니다:

-   URL: `http://localhost:15672`
-   사용자명: `rabbitmq`
-   비밀번호: `1234`

## 🧪 테스트

```bash
# 단위 테스트 실행
./mvnw test

# 특정 테스트 클래스 실행
./mvnw test -Dtest=SampleApplicationTests
```

## 📚 참고 자료

-   [Spring AMQP 공식 문서](https://docs.spring.io/spring-amqp/docs/current/reference/html/)
-   [RabbitMQ 공식 문서](https://www.rabbitmq.com/documentation.html)
-   [Spring Boot AMQP 가이드](https://spring.io/guides/gs/messaging-rabbitmq/)
