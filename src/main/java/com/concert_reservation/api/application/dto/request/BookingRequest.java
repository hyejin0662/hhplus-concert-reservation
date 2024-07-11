package com.concert_reservation.api.application.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
	private String userId;
	private Long concertOptionId;
	private Long seatId;
	private LocalDateTime bookingTime;

	public BookingCommand toCommand() {
		return DtoConverter.convert(this, BookingCommand.class);
	}
}
