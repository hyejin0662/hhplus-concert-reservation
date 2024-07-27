package com.concert_reservation.api.domain.concert;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.concert.Booking;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCommand {
    private String userId;
    private Long concertOptionId;
    private Long seatId;
    private LocalDateTime bookingTime;

    public Booking toEntity() {
        return DtoConverter.convert(this, Booking.class);
    }
}