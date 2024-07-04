package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.ResponseResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private ResponseResult responseResult;
    private Long bookingId;
    private BookingStatus bookingStatus;
    private LocalDateTime bookingTime;
    private UserResponse user;
    private ConcertResponse concert;
}