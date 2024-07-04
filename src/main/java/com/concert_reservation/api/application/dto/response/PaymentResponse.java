package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.common.type.ResponseResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private ResponseResult responseResult;
    private BookingResponse bookingResponse;
}