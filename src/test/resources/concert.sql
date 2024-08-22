
CREATE TABLE IF NOT EXISTS `User` (
                                      user_id VARCHAR(255) PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
                                      email VARCHAR(255) NOT NULL,
                                      phone_number VARCHAR(255) NOT NULL
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

CREATE TABLE IF NOT EXISTS concert_option (
                                              concert_option_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              concert_id BIGINT NOT NULL,
                                              singer_name VARCHAR(255) NOT NULL,
                                              concert_date DATETIME NOT NULL,
                                              capacity BIGINT NOT NULL,
                                              location VARCHAR(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS Booking (
                                       booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       user_id VARCHAR(255) NOT NULL,
                                       seat_id BIGINT NOT NULL,
                                       booking_status VARCHAR(255) NOT NULL,
                                       booking_time DATETIME NOT NULL

);

CREATE TABLE IF NOT EXISTS Point (
                                     point_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(255) NOT NULL,
                                     amount BIGINT NOT NULL,
                                     payment_time DATETIME NOT NULL,
                                     payment_method VARCHAR(255) NOT NULL,
                                     version BIGINT NOT NULL DEFAULT 0

);

CREATE TABLE IF NOT EXISTS Token (
                                     token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(255) NOT NULL,
                                     waiting_at DATETIME NOT NULL,
                                     expiration_at DATETIME NOT NULL,
                                     token_status VARCHAR(255) NOT NULL,
                                     waiting_number INT NOT NULL

);

CREATE TABLE IF NOT EXISTS waiting_count (
                                             count_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             count BIGINT NOT NULL
);

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `User` (user_id, name, email, phone_number) VALUES
                                                                     ('user1', '김철수', 'chulsoo@example.com', '01012345678'),
                                                                     ('user2', '이영희', 'younghee@example.com', '01087654321'),
                                                                     ('user10', '박영수', 'youngsoo@example.com', '01023456789')
ON DUPLICATE KEY UPDATE
                     name = VALUES(name),
                     email = VALUES(email),
                     phone_number = VALUES(phone_number);


INSERT INTO Concert (concert_id, concert_name) VALUES
                                                   (1, '록 콘서트'),
                                                   (2, '재즈 콘서트')
ON DUPLICATE KEY UPDATE
    concert_name = VALUES(concert_name);


INSERT INTO concert_option (concert_option_id, concert_id, singer_name, concert_date, capacity, location) VALUES
                                                                                                              (1, 1, '록 밴드', '2024-08-01 20:00:00', 100, '스타디움'),
                                                                                                              (2, 2, '재즈 밴드', '2024-08-15 19:00:00', 50, '콘서트 홀')
ON DUPLICATE KEY UPDATE
                     singer_name = VALUES(singer_name),
                     concert_date = VALUES(concert_date),
                     capacity = VALUES(capacity),
                     location = VALUES(location);


INSERT INTO Seat (seat_id, seat_number, is_reserved, price) VALUES
                                                                (1, 1, FALSE, 100),
                                                                (2, 2, FALSE, 100),
                                                                (3, 3, FALSE, 150),
                                                                (4, 4, FALSE, 150)
ON DUPLICATE KEY UPDATE
                     seat_number = VALUES(seat_number),
                     is_reserved = VALUES(is_reserved),
                     price = VALUES(price);


INSERT INTO Token (token_id, user_id, waiting_at, expiration_at, token_status, waiting_number) VALUES
    (1, 'user1', '2024-07-01 10:00:00', '2024-07-01 10:30:00', 'WAITING', 1)
ON DUPLICATE KEY UPDATE
                     user_id = VALUES(user_id),
                     waiting_at = VALUES(waiting_at),
                     expiration_at = VALUES(expiration_at),
                     token_status = VALUES(token_status),
                     waiting_number = VALUES(waiting_number);


INSERT INTO waiting_count (count_id, count) VALUES
    (1, 0)
ON DUPLICATE KEY UPDATE
    count = VALUES(count);


INSERT INTO Booking (booking_id, user_id, seat_id, booking_status, booking_time) VALUES
                                                                                     (1, 'user1', 1, 'CONFIRMED', '2024-07-15 10:00:00'),
                                                                                     (2, 'user2', 2, 'CONFIRMED', '2024-07-16 11:00:00'),
                                                                                     (3, 'user10', 3, 'CONFIRMED', '2024-07-17 12:00:00')
ON DUPLICATE KEY UPDATE
                     user_id = VALUES(user_id),
                     seat_id = VALUES(seat_id),
                     booking_status = VALUES(booking_status),
                     booking_time = VALUES(booking_time);


INSERT INTO Point (point_id, user_id, amount, payment_time, payment_method, version) VALUES
                                                                                         (1, 'user1', 1000, '2024-07-01 10:00:00', 'CREDIT_CARD', 0),
                                                                                         (2, 'user2', 500, '2024-07-01 10:00:00', 'BANK_TRANSFER', 0),
                                                                                         (3, 'user10', 2000, '2024-07-01 11:00:00', 'PAYPAL', 0)
ON DUPLICATE KEY UPDATE
                     user_id = VALUES(user_id),
                     amount = VALUES(amount),
                     payment_time = VALUES(payment_time),
                     payment_method = VALUES(payment_method),
                     version = VALUES(version);
