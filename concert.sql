CREATE TABLE User (
                      user_id VARCHAR(255) PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      phone_number VARCHAR(255) NOT NULL,
                      balance BIGINT NOT NULL
);

CREATE TABLE Seat (
                      seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      seat_number INT NOT NULL,
                      is_reserved BOOLEAN NOT NULL,
                      price INT NOT NULL
);

CREATE TABLE Concert (
                         concert_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         concert_name VARCHAR(255) NOT NULL
);

CREATE TABLE ConcertOption (
                               concert_option_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               concert_id BIGINT NOT NULL,
                               singer_name VARCHAR(255) NOT NULL,
                               concert_date DATETIME NOT NULL,
                               capacity BIGINT NOT NULL,
                               location VARCHAR(255) NOT NULL,
                               FOREIGN KEY (concert_id) REFERENCES Concert(concert_id)
);

CREATE TABLE Booking (
                         booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id VARCHAR(255) NOT NULL,
                         seat_id BIGINT NOT NULL,
                         booking_status VARCHAR(255) NOT NULL,
                         booking_time DATETIME NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES User(user_id),
                         FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
);

CREATE TABLE Point (
                       point_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id VARCHAR(255) NOT NULL,
                       balance BIGINT NOT NULL,
                       amount BIGINT NOT NULL,
                       payment_time DATETIME NOT NULL,
                       payment_method VARCHAR(255) NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Token (
                       token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id VARCHAR(255) NOT NULL,
                       waiting_at DATETIME NOT NULL,
                       expiration_at DATETIME NOT NULL,
                       token_status VARCHAR(255) NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE WaitingCount (
                              count_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              count BIGINT NOT NULL
);

-- Optional table for mapping seats to concert options if necessary
CREATE TABLE ConcertOption_Seat (
                                    concert_option_id BIGINT NOT NULL,
                                    seat_id BIGINT NOT NULL,
                                    PRIMARY KEY (concert_option_id, seat_id),
                                    FOREIGN KEY (concert_option_id) REFERENCES ConcertOption(concert_option_id),
                                    FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
);
