package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.ResponseResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private ResponseResult responseResult;
    private Long bookingId;
    private BookingStatus bookingStatus;
    private LocalDateTime bookingTime;
    private UserResponse user;
    private ConcertResponse concert;

    public static BookingResponse from(Booking booking) {
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .bookingTime(booking.getBookingTime())
            .user(UserResponse.from(booking.getUser()))
            .concert(ConcertResponse.from(booking.getSeat().getConcert()))
            .build();
    }

    public Booking toEntity() {
        return Booking.builder()
            .bookingId(this.bookingId)
            .bookingTime(this.bookingTime)
            .user(this.user.toEntity())
            .seat(this.concert.getSeats().get(0).toEntity())
            .build();
    }
}