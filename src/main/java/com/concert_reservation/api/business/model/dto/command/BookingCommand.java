package com.concert_reservation.api.business.model.dto.command;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.BookingStatus;

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
    private BookingStatus bookingStatus;

    public Booking toEntity() {
        return DtoConverter.convert(this, Booking.class);
    }
}