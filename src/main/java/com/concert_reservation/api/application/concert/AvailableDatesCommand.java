package com.concert_reservation.api.application.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableDatesCommand {
	private Long concertOptionId;
	private Long concertId;
}
