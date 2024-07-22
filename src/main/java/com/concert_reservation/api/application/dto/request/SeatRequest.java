package com.concert_reservation.api.application.dto.request;

import java.time.LocalDate;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequest {
	private Long concertOptionId;
}
