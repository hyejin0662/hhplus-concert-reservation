package com.concert_reservation.api.application.dto.request;

import com.concert_reservation.api.business.model.dto.command.AvailableDatesCommand;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDatesRequest {
	private Long concertOptionId;

	public AvailableDatesCommand toCommand() {
		return DtoConverter.convert(this, AvailableDatesCommand.class);
	}
}
