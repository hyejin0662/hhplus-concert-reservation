package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCommand {
    private Long bookingId;
    private String userId;
    private Long seatId;
    private LocalDateTime bookingTime;
}