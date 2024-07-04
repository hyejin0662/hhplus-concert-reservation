package com.concert_reservation.api.application.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSeatsRequest {
	private Long concertId;
	private LocalDate date;
}