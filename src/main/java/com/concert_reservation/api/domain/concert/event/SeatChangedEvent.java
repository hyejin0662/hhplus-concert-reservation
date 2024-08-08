package com.concert_reservation.api.domain.concert.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatChangedEvent {
	private final Long seatId;
	private final boolean isReserved;
}