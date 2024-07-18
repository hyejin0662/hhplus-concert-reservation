CREATE TABLE IF NOT EXISTS `User` (
                                      user_id VARCHAR(255) PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
                                      email VARCHAR(255) NOT NULL,
                                      phone_number VARCHAR(255) NOT NULL,
                                      balance BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Seat (
                                    seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    seat_number INT NOT NULL,
                                    is_reserved BOOLEAN NOT NULL,
                                    price INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Concert (
                                       concert_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       concert_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ConcertOption (
                                             concert_option_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             concert_id BIGINT NOT NULL,
                                             singer_name VARCHAR(255) NOT NULL,
                                             concert_date DATETIME NOT NULL,
                                             capacity BIGINT NOT NULL,
                                             location VARCHAR(255) NOT NULL,
                                             FOREIGN KEY (concert_id) REFERENCES Concert(concert_id)
);

CREATE TABLE IF NOT EXISTS Booking (
                                       booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       user_id VARCHAR(255) NOT NULL,
                                       seat_id BIGINT NOT NULL,
                                       booking_status VARCHAR(255) NOT NULL,
                                       booking_time DATETIME NOT NULL,
                                       FOREIGN KEY (user_id) REFERENCES `User`(user_id),
                                       FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
);

CREATE TABLE IF NOT EXISTS Point (
                                     point_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(255) NOT NULL,
                                     balance BIGINT NOT NULL,
                                     amount BIGINT NOT NULL,
                                     payment_time DATETIME NOT NULL,
                                     payment_method VARCHAR(255) NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES `User`(user_id)
);

CREATE TABLE IF NOT EXISTS Token (
                                     token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(255) NOT NULL,
                                     waiting_at DATETIME NOT NULL,
                                     expiration_at DATETIME NOT NULL,
                                     token_status VARCHAR(255) NOT NULL,
                                     waiting_number INT NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES `User`(user_id)
);

CREATE TABLE IF NOT EXISTS WaitingCount (
                                            count_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            count BIGINT NOT NULL
);

-- Optional table for mapping seats to concert options if necessary
CREATE TABLE IF NOT EXISTS ConcertOption_Seat (
                                                  concert_option_id BIGINT NOT NULL,
                                                  seat_id BIGINT NOT NULL,
                                                  PRIMARY KEY (concert_option_id, seat_id),
                                                  FOREIGN KEY (concert_option_id) REFERENCES ConcertOption(concert_option_id),
                                                  FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
);


-- Insert data only if the user does not already exist
INSERT INTO `User` (user_id, name, email, phone_number, balance) VALUES
                                                                     ('user1', '김철수', 'chulsoo@example.com', '01012345678', 1000),
                                                                     ('user2', '이영희', 'younghee@example.com', '01087654321', 500)
ON DUPLICATE KEY UPDATE
                     name = VALUES(name),
                     email = VALUES(email),
                     phone_number = VALUES(phone_number),
                     balance = VALUES(balance);

-- Insert data only if the concert does not already exist
INSERT INTO Concert (concert_id, concert_name) VALUES
                                                   (1, '록 콘서트'),
                                                   (2, '재즈 콘서트')
ON DUPLICATE KEY UPDATE
    concert_name = VALUES(concert_name);

-- Insert data only if the concert option does not already exist
INSERT INTO ConcertOption (concert_option_id, concert_id, singer_name, concert_date, capacity, location) VALUES
                                                                                                             (1, 1, '록 밴드', '2024-08-01 20:00:00', 100, '스타디움'),
                                                                                                             (2, 2, '재즈 밴드', '2024-08-15 19:00:00', 50, '콘서트 홀')
ON DUPLICATE KEY UPDATE
                     singer_name = VALUES(singer_name),
                     concert_date = VALUES(concert_date),
                     capacity = VALUES(capacity),
                     location = VALUES(location);

-- Insert data only if the seat does not already exist
INSERT INTO Seat (seat_id, seat_number, is_reserved, price) VALUES
                                                                (1, 1, FALSE, 100),
                                                                (2, 2, FALSE, 100),
                                                                (3, 3, FALSE, 150),
                                                                (4, 4, FALSE, 150)
ON DUPLICATE KEY UPDATE
                     seat_number = VALUES(seat_number),
                     is_reserved = VALUES(is_reserved),
                     price = VALUES(price);

-- Insert data only if the token does not already exist
INSERT INTO Token (token_id, user_id, waiting_at, expiration_at, token_status, waiting_number) VALUES
    (1, 'user1', '2024-07-01 10:00:00', '2024-07-01 10:30:00', 'WAITING', 1)
ON DUPLICATE KEY UPDATE
                     user_id = VALUES(user_id),
                     waiting_at = VALUES(waiting_at),
                     expiration_at = VALUES(expiration_at),
                     token_status = VALUES(token_status),
                     waiting_number = VALUES(waiting_number);

-- Insert data only if the waiting count does not already exist
INSERT INTO WaitingCount (count_id, count) VALUES
    (1, 0)
ON DUPLICATE KEY UPDATE
    count = VALUES(count);