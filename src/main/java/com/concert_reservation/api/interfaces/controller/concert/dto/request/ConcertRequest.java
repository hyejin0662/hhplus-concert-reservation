package com.concert_reservation.api.interfaces.controller.concert.dto.request;

import com.concert_reservation.api.application.concert.ConcertCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConcertRequest {
    private String concertName;

	public ConcertCommand toCommand() {
		return ConcertCommand.builder()
			.concertName(concertName)
			.build();
	}
}
