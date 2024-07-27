package com.concert_reservation.api.interfaces.controller.conert.dto;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.concert.BookingCommand;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {
	private String userId;
	private Long concertOptionId;
	private Long seatId;
	private LocalDateTime bookingTime;

	public BookingCommand toCommand() {
		return DtoConverter.convert(this, BookingCommand.class);
	}
}
