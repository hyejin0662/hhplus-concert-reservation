package com.concert_reservation.api.interfaces.controller.concert.dto.request;

import java.time.LocalDateTime;

import com.concert_reservation.api.application.concert.AvailableDatesCommand;
import com.concert_reservation.api.application.concert.BookingCommand;
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

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AvailableDates {
		private Long concertOptionId;

		public AvailableDatesCommand toCommand() {
			return DtoConverter.convert(this, AvailableDatesCommand.class);
		}
	}

}
