package com.concert_reservation.api.interfaces.controller.conert.dto;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.concert.BookingInfo;
import com.concert_reservation.api.domain.concert.Booking;
import com.concert_reservation.api.interfaces.controller.user.dto.UserResponse;
import com.concert_reservation.common.mapper.DtoConverter;
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
        private SeatResponse seat;

        public static BookingResponse from(BookingInfo bookingInfo) {
                return DtoConverter.convert(bookingInfo, BookingResponse.class);
        }

        public Booking toEntity() {
            return Booking.builder()
                .bookingId(this.bookingId)
                .bookingStatus(this.bookingStatus)
                .bookingTime(this.bookingTime)
                .user(this.user.toEntity())
                .seat(this.seat.toEntity())
                .build();
        }
}
