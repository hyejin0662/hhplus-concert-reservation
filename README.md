# hhplus-concert-reservation
항해플러스 3주차 콘서트 예약 프로젝트

<details>
  <summary>시퀀스 다이어그램</summary>

### 1. 유저 토큰 발급 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/16dd3f63-43ff-4dbd-877e-3182532102b6)

### 2. 예매 가능 날짜 조회 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/7e73b6ab-8be8-46a0-8eb8-7e05b43b75eb)

### 3. 해당 날짜의 좌석 조회 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/1dd1084f-1b7e-4c0c-b11d-5580c30433b5)

### 4. 좌석 예매 요청 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/27e11a82-8466-4d9d-ac3b-515fb0ce9b23)

### 5. 잔액 조회 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/4af59cd8-7f29-4ea8-b753-7c3f0f105387)

### 6. 잔액 충전 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/91e7f5cd-c8af-4f43-88b7-076ce37a0637)

### 7. 결제 API

![image](https://github.com/hyejin0662/hhplus-concert-reservation/assets/110523580/68c517fd-7788-4506-ae49-5d499c8ccac7)
</details>

<details>
  <summary>ERD</summary>

## ERD 구성 요소
### User Table (유저 테이블)
| 필드명  | 영문명     | 타입     | NOT NULL |
|---------|------------|----------|----------|
| 유저 ID | user_id    | varchar  | YES      |
| 이름    | name       | varchar  | YES      |
| 이메일  | email      | varchar  | YES      |

### Token Table (유저 토큰 테이블)
| 필드명        | 영문명        | 타입      | NOT NULL |
|---------------|---------------|-----------|----------|
| 토큰 ID       | token_id      | bigint    | YES      |
| 유저 ID       | user_id       | varchar   | YES      |
| 콘서트 코드   | concert_code  | varchar   | YES      |
| 유효 시간     | expiration_time | timestamp | YES     |

### Concert Table (콘서트 테이블)
| 필드명        | 영문명        | 타입      | NOT NULL |
|---------------|---------------|-----------|----------|
| 콘서트 ID     | concert_id    | bigint    | YES      |
| 이름          | name          | varchar   | YES      |
| 날짜          | date          | date      | YES      |

### Seat Table (좌석 테이블)
| 필드명        | 영문명        | 타입      | NOT NULL |
|---------------|---------------|-----------|----------|
| 좌석 ID       | seat_id       | bigint    | YES      |
| 콘서트 ID     | concert_id    | bigint    | YES      |
| 좌석 번호     | seat_number   | int       | YES      |
| 예약 여부     | is_reserved   | boolean   | YES      |

### Reservation Table (예약 테이블)
| 필드명       | 영문명            | 타입        | NOT NULL |
|-----------|-------------------|-----------|----------|
| 예약 ID     | reservation_id    | bigint    | YES      |
| 콘서트 옵션 Id | concertOptionId | bigint        | YES      |
| 유저 ID     | user_id           | varchar   | YES      |
| 좌석 ID     | seat_id           | bigint    | YES      |
| 예약 시간     | reservation_time  | timestamp | YES      |
| 예약 확정 여부  | is_confirmed      | boolean   | YES      |

### Point Table (포인트 테이블)
| 필드명        | 영문명     | 타입      | NOT NULL |
|---------------|------------|-----------|----------|
| 포인트 ID     | point_id   | bigint    | YES      |
| 유저 ID       | user_id    | varchar   | YES      |
| 잔액          | amount     | decimal   | YES      |

### TempReservation Table (임시 예약 테이블)
| 필드명                | 영문명                | 타입      | NOT NULL |
|-----------------------|-----------------------|-----------|----------|
| 임시 예약 ID          | temp_reservation_id   | bigint    | YES      |
| 유저 ID               | user_id               | varchar   | YES      |
| 좌석 ID               | seat_id               | bigint    | YES      |
| 임시 예약 시간        | temp_reservation_time | timestamp | YES      |
| 만료 시간             | expiration_time       | timestamp | YES      |

### Queue Table (큐 테이블)
| 필드명          | 영문명     | 타입      | NOT NULL |
|-----------------|------------|-----------|----------|
| 큐 ID           | queue_id   | bigint    | YES      |
| 유저 ID         | user_id    | varchar   | YES      |
| 콘서트 ID       | concert_id | bigint    | YES      |
| 큐 등록 시간    | queue_time | timestamp | YES      |
| 대기열 위치     | position   | int       | YES      |

**설명**

1. USER는 여러 TOKEN을 가질 수 있습니다.
2. USER는 여러 RESERVATION을 할 수 있습니다.
3. USER는 하나의 POINT를 가집니다.
4. USER는 여러 TEMP_RESERVATION을 할 수 있습니다.
5. USER는 여러 QUEUE를 가질 수 있습니다.
6. CONCERT는 여러 SEAT를 포함합니다.
7. CONCERT는 여러 QUEUE를 포함합니다.
8. SEAT는 여러 RESERVATION에 포함될 수 있습니다.
9. SEAT는 여러 TEMP_RESERVATION에 포함될 수 있습니다.
</details>

<details>
  <summary>API Documentation</summary>

## 유저 토큰 발급 API

### Request

- **Method**: POST
- **URL**: `http://localhost:8082/mock/queue`
- **Content-Type**: application/json

```json
{
  "userId": "spring123",
  "requestedTime": "2024-07-03T10:00:00",
  "priority": 2
}
```

### Response

- **Status Code**: 200
- **Content-Type**: application/json

```json
{
  "queueToken": "ca20bc2a-577b-4055-ab3b-7e197c668b35",
  "position": 0,
  "issueTime": [
    2024,
    7,
    5,
    1,
    55,
    10,
    816342400
  ],
  "expirationTime": [
    2024,
    7,
    5,
    2,
    0,
    10,
    816342400
  ],
  "queueStatus": "PROCESSING"
}
```

## 예약 가능한 날짜 목록 조회 API

### Request

- **Method**: GET
- **URL**: `http://localhost:8082/mock/concerts`

### Response

- **Status Code**: 200
- **Content-Type**: application/json

```json
{
  "concerts": [
    {
      "concertId": 1,
      "name": "concertA",
      "date": [
        2024,
        7,
        6,
        1,
        55,
        12,
        127111700
      ],
      "seats": [
        {
          "seatId": 0,
          "seatNumber": 1,
          "reserved": false
        },
        ...
      ]
    },
    ...
  ]
}
```

## 특정 날짜의 예약 가능한 좌석 목록 조회 API

### Request

- **Method**: GET
- **URL**: `http://localhost:8082/mock/available-seats?concertId=1&date=2024-07-10`

### Response

- **Status Code**: 200
- **Content-Type**: application/json

```json
[
  {
    "seatId": 0,
    "seatNumber": 1,
    "reserved": false
  },
  ...
]
```

## 좌석 예약 요청 API

### Request

- **Method**: POST
- **URL**: `http://localhost:8082/mock/booking`
- **Content-Type**: application/json
- **Headers**:
    - `Queue-Token`: your-queue-token

```json
{
  "userId": 1,
  "concertOptionId": 1,
  "seats": "A_10,A_11"
}
```

### Response

- **Status Code**: 200
- **Content-Type**: application

/json

```json
{
  "responseResult": "SUCCESS",
  "bookingId": 1,
  "bookingStatus": "COMPLETE",
  "bookingTime": [
    2024,
    7,
    5,
    1,
    55,
    14,
    863645100
  ],
  "user": {
    "userId": 1,
    "name": "UserA",
    "balance": 100000
  },
  "concert": {
    "concertId": 1,
    "name": "concertA",
    "date": [
      2024,
      7,
      10,
      1,
      55,
      14,
      863645100
    ],
    "seats": [
      {
        "seatId": 0,
        "seatNumber": 10,
        "reserved": true
      },
      {
        "seatId": 1,
        "seatNumber": 11,
        "reserved": true
      }
    ]
  }
}
```

## 잔액 충전 API

### Request

- **Method**: POST
- **URL**: `http://localhost:8082/mock/balance`
- **Content-Type**: application/json

```json
{
  "userId": 1,
  "balance": 100.00
}
```

### Response

- **Status Code**: 200
- **Content-Type**: application/json

```json
{
  "userId": 1,
  "name": "아무개",
  "balance": 100
}
```

## 잔액 조회 API

### Request

- **Method**: GET
- **URL**: `http://localhost:8082/mock/balance/1`

### Response

- **Status Code**: 200
- **Content-Type**: application/json

```json
{
  "userId": 1,
  "name": "아무개",
  "balance": 10000
}
```

## 결제 API

### Request

- **Method**: POST
- **URL**: `http://localhost:8082/mock/payment`
- **Content-Type**: application/json
- **Headers**:
    - `Queue-Token`: your-queue-token

```json
{
  "bookingId": 12345,
  "concertId": 1,
  "userId": 1001,
  "seats": "A_10,A_11"
}
```

### Response

- **Status Code**: 200
- **Content-Type**: application/json

```json
{
  "responseResult": "SUCCESS",
  "bookingResponse": {
    "responseResult": "SUCCESS",
    "bookingId": 12345,
    "bookingStatus": "COMPLETE",
    "bookingTime": [
      2024,
      7,
      5,
      1,
      57,
      40,
      281699400
    ],
    "user": {
      "userId": 1001,
      "name": "UserA",
      "balance": 100000
    },
    "concert": {
      "concertId": 1,
      "name": "concertA",
      "date": [
        2024,
        7,
        10,
        1,
        57,
        40,
        281699400
      ],
      "seats": [
        {
          "seatId": 0,
          "seatNumber": 10,
          "reserved": true
        },
        {
          "seatId": 1,
          "seatNumber": 11,
          "reserved": true
        }
      ]
    }
  }
}
```






</details>
<details>
  <summary>마일스톤</summary>
# 마일스톤

https://github.com/hyejin0662/hhplus-concert-reservation/milestones
</details>
<details>
  <summary>swagger</summary>
# swagger
http://localhost:8082/swagger-ui/index.html

![img.png](img.png)
![img_1.png](img_1.png)
</details>

<details>




 <summary> 예외 slcak 알림 </summary>
![img_2.png](img_2.png)

(에러 로그 내용은 추후 고도화하겠습니다 ^^..!)
</details>



<details>
<summary> 회고 </summary>

  
### 회고록

## Chapter 2 - 3주차

#### Step 5

* **Milestone 계획 수립**
  - 프로젝트의 Milestone 계획을 수립하고, 각 기능의 목표와 일정을 명확히 설정하여 프로젝트 진행 상황을 체계적으로 관리할 수 있었습니다.
* **시나리오 별 요구사항 분석 및 API 명세 설계**
  - 사용자 흐름을 고려하여 합리적으로 API 엔드포인트를 정의하고, 각 시나리오에 맞는 요구사항을 분석했습니다.

#### Step 6

* **ERD 설계 자료 작성**
  - 데이터베이스 구조를 시각적으로 표현하여 각 테이블 간의 관계와 필요한 필드를 명확히 했습니다.
* **API 명세 및 Mock API 작성**
  - 실제 서버 구현 전에 API 동작을 미리 검증할 수 있도록 API 명세 및 Mock API를 작성했습니다.
* **서버 애플리케이션 구동 가능하도록 작성**
  - 기본적인 설정과 함께 주요 기능을 구현하여 서버 애플리케이션을 구동 가능하도록 작성했습니다.

**회고:**
* Milestone 계획과 API 명세를 통해 프로젝트의 방향성을 명확히 할 수 있었습니다.
* ERD 설계를 통해 데이터베이스 구조를 명확히 이해하고, 필요한 테이블과 필드를 정의할 수 있었습니다.
* Mock API를 통해 실제 서버 구현 전에 API 동작을 검증할 수 있었습니다.

## Chapter 2 - 4주차

#### Step 7

* **Swagger 문서 작성**
  - 각 엔드포인트와 그에 따른 요청 및 응답 구조를 명확히 정의하여, 개발 중 혼란을 최소화할 수 있었습니다.
* **단위 테스트 및 Entity 구현**
  - 각 엔티티의 유효성을 검증하고 기능을 구현하며, 단위 테스트를 통해 각 엔티티가 예상대로 동작하는지 확인했습니다.

#### Step 8

* **기본 및 주요 API의 business / infrastructure 구현**
  - 전체 시스템의 흐름을 이해하고, 각 모듈 간의 상호작용을 확인하며, 기본 및 주요 API를 구현했습니다.
* **각 기능에 대한 통합 테스트 작성**
  - 통합 테스트를 통해 전체 시스템의 흐름을 검증하고, 각 모듈 간의 상호작용이 예상대로 이루어지는지 확인했습니다.

**회고:**
* Swagger 문서를 통해 API 명세를 시각적으로 확인할 수 있어, 개발 중 혼란을 최소화할 수 있었습니다.
* 단위 테스트와 통합 테스트를 통해 시스템의 안정성을 높이고, 예상치 못한 문제를 사전에 발견할 수 있었습니다.
* 기본 및 주요 API를 구현하며, 전체 시스템의 흐름을 이해하고, 각 모듈 간의 상호작용을 확인할 수 있었습니다.

## Chapter 2 - 5주차

#### Step 9

* **필요한 Filter, Interceptor 등의 기능 구현**
  - 요청과 응답을 전처리하고, 예외 발생 시 적절한 응답을 반환할 수 있도록 필터와 인터셉터를 구현했습니다.
  - Filter에서 logback 정보를 출력하였고, Interceptor를 통해 토큰 검증을 구현했습니다.
* **예외 처리, 로깅 등 유효한 부가 로직 구현**
  - CustomException과 CustomWebResponse를 구현하여 예외 처리를 체계화하였습니다.
  - 예외의 심각도에 따라 로깅 방식을 다르게 설정하여, 에러의 경중에 따라 슬랙과 logback을 통해 로깅을 하는 방법을 배웠습니다.

#### Step 10

* **미비한 이전 과제 진행사항 보완**
  - 이전에 미비했던 과제 진행 사항을 보완하며, 전체 프로젝트의 완성도를 높였습니다.
* **제공해야 하는 API 완성**
  - 각 API의 기능을 최적화하고, 코드의 가독성과 유지보수성을 높이기 위해 리팩토링 작업을 진행했습니다.
  - 추가적인 테스트를 통해 각 기능이 정상적으로 동작하는지 확인했습니다.

**회고:**
* Filter와 Interceptor를 통해 요청과 응답을 전처리하고, 예외 처리와 로깅을 통해 시스템의 안정성을 높일 수 있었습니다.
* 예외 처리를 체계화하고, 심각도에 따라 로깅 방식을 다르게 설정함으로써, 문제 발생 시 신속하게 대응하는 방법을 배울 수 있었습니다.
* 이전 과제의 미비한 부분을 보완하며, 전체 프로젝트의 완성도를 높이고자 했습니다.

 </details>
