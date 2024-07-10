package com.concert_reservation.api.application.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointRequest {
    private Long bookingId;
    private String userId;
    private String paymentMethod;
    private Long amount;
    private Long balance;
    private LocalDateTime paymentTime;
}
