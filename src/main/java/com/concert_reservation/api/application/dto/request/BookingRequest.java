package com.concert_reservation.api.application.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.business.model.entity.Seat;

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
	private Long concertId;
	private List<Long> seatIds;

}
