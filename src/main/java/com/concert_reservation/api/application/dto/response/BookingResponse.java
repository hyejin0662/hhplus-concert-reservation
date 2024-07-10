package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.ResponseResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    private SeatResponse seat;

    public static BookingResponse from(BookingInfo booking, UserInfo userInfo) {
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .bookingStatus(booking.getBookingStatus())
            .bookingTime(booking.getBookingTime())
            .user(UserResponse.from(userInfo))
            .seat(SeatResponse.from(booking.getSeatId()))
            .build();
    }

    public Booking toEntity() {
        return Booking.builder()
            .bookingId(this.bookingId)
            .bookingStatus(this.bookingStatus)
            .bookingTime(this.bookingTime)
            .userId(this.user.getUserId())
            .seatId(this.seat.getSeatId())
            .build();
    }
}
