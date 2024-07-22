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
public class ConcertOptionRequest {
	private Long concertOptionId;
	private Long concertId;
	private String singerName;
	private LocalDateTime concertDate;
	private Long capacity;
	private String location;
}
