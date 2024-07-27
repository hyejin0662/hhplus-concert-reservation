package com.concert_reservation.api.interfaces.controller.conert.dto;

import java.time.LocalDate;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.concert.Seat;
import com.concert_reservation.api.domain.concert.SeatInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {
	class Request {
		private Long concertOptionId;
	}

	class Response {
		private Long seatId;
		private int seatNumber;
		private boolean isReserved;
		//    private String concertId;
		private ConcertResponse concertResponse;

		// public static Response from(SeatInfo seatInfo) {
		// 	return Response.builder()
		// 		.seatId(seatInfo.getSeatId())
		// 		.seatNumber(seatInfo.getSeatNumber())
		// 		.isReserved(seatInfo.isReserved())
		// 		.build();
		// }
		//
		// public Seat toEntity() {
		// 	return Seat.builder()
		// 		.seatId(this.seatId)
		// 		.seatNumber(this.seatNumber)
		// 		.isReserved(this.isReserved)
		// 		.build();
		// }
	}
}
