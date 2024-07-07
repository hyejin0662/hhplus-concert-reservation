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


# 마일스톤

https://github.com/hyejin0662/hhplus-concert-reservation/milestones

</details>