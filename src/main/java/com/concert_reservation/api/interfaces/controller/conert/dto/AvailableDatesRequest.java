package com.concert_reservation.api.interfaces.controller.conert.dto;

import com.concert_reservation.api.domain.concert.AvailableDatesCommand;
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
