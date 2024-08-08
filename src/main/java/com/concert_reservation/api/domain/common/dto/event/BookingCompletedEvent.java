package com.concert_reservation.api.domain.common.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingCompletedEvent {
    private String userId;
    private Long concertOptionId;
}