CREATE TABLE User (
                      userId VARCHAR(255) PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255),
                      phoneNumber VARCHAR(255) NOT NULL,
                      balance BIGINT NOT NULL
);

CREATE TABLE Concert (
                         concertId BIGINT AUTO_INCREMENT PRIMARY KEY,
                         concertName VARCHAR(255) NOT NULL
);

CREATE TABLE Seat (
                      seatId BIGINT AUTO_INCREMENT PRIMARY KEY,
                      concertId BIGINT NOT NULL,
                      seatNumber INT NOT NULL,
                      isReserved BOOLEAN NOT NULL,
                      FOREIGN KEY (concertId) REFERENCES Concert(concertId)
);

CREATE TABLE Booking (
                         bookingId BIGINT AUTO_INCREMENT PRIMARY KEY,
                         userId VARCHAR(255) NOT NULL,
                         seatId BIGINT NOT NULL,
                         bookingStatus VARCHAR(255) NOT NULL,
                         bookingTime TIMESTAMP NOT NULL,
                         FOREIGN KEY (userId) REFERENCES User(userId),
                         FOREIGN KEY (seatId) REFERENCES Seat(seatId)
);

CREATE TABLE Point (
                       pointId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       userId VARCHAR(255) NOT NULL,
                       balance BIGINT NOT NULL,
                       amount BIGINT NOT NULL,
                       paymentTime TIMESTAMP NOT NULL,
                       paymentMethod VARCHAR(255) NOT NULL,
                       FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE ConcertOption (
                               concertOptionId BIGINT AUTO_INCREMENT PRIMARY KEY,
                               concertId BIGINT NOT NULL,
                               singerName VARCHAR(255) NOT NULL,
                               concertDate TIMESTAMP NOT NULL,
                               capacity BIGINT NOT NULL,
                               location VARCHAR(255) NOT NULL,
                               FOREIGN KEY (concertId) REFERENCES Concert(concertId)
);

CREATE TABLE WaitingCount (
                              countId BIGINT AUTO_INCREMENT PRIMARY KEY,
                              count BIGINT NOT NULL,
                              concertId BIGINT NOT NULL,
                              FOREIGN KEY (concertId) REFERENCES Concert(concertId)
);

CREATE TABLE Token (
                       tokenId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       userId VARCHAR(255) NOT NULL,
                       waitingAt TIMESTAMP NOT NULL,
                       expirationAt TIMESTAMP NOT NULL,
                       tokenStatus VARCHAR(255) NOT NULL,
                       waitingCountId BIGINT NOT NULL,
                       FOREIGN KEY (userId) REFERENCES User(userId),
                       FOREIGN KEY (waitingCountId) REFERENCES WaitingCount(countId)
);
-- 유저 데이터 삽입
INSERT INTO User (userId, name, email, phoneNumber, balance) VALUES
                                                                 ('user1', '사용자1', 'user1@example.com', '010-1234-0001', 1000),
                                                                 ('user2', '사용자2', 'user2@example.com', '010-1234-0002', 1000),
-- ... 3 to 99
                                                                 ('user99', '사용자99', 'user99@example.com', '010-1234-0099', 1000),
                                                                 ('user100', '사용자100', 'user100@example.com', '010-1234-0100', 1000);

-- 콘서트 데이터 삽입
INSERT INTO Concert (concertName) VALUES ('서울 콘서트');

-- 콘서트 아이디 가져오기
SET @concertId = (SELECT concertId FROM Concert WHERE concertName = '서울 콘서트');

-- WaitingCount 데이터 삽입
INSERT INTO WaitingCount (count, concertId) VALUES (50, @concertId);

-- WaitingCount 아이디 가져오기
SET @waitingCountId = (SELECT countId FROM WaitingCount WHERE concertId = @concertId);

-- Token 데이터 삽입 (WAITING 상태 50명)
INSERT INTO Token (userId, waitingAt, expirationAt, tokenStatus, waitingCountId)
SELECT userId, NOW(), NOW() + INTERVAL 8 MINUTE, 'WAITING', @waitingCountId
FROM User
WHERE userId BETWEEN 'user1' AND 'user50';

-- Token 데이터 삽입 (PROCESSING 상태 50명)
INSERT INTO Token (userId, waitingAt, expirationAt, tokenStatus, waitingCountId)
SELECT userId, NOW(), NOW() + INTERVAL 8 MINUTE, 'PROCESSING', @waitingCountId
FROM User
WHERE userId BETWEEN 'user51' AND 'user100';
