package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BookingCommand {
    private String userId;
    private Long seatId;
    private LocalDateTime bookingTime;
    private boolean isConfirmed;

    public Booking toEntity(User user, Seat seat) {
        return Booking.builder()
            .user(user)
            .seat(seat)
            .bookingTime(this.bookingTime)
            .isConfirmed(this.isConfirmed)
            .build();
    }
}
