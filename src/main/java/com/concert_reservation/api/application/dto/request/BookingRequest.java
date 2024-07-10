package com.concert_reservation.api.application.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.business.model.entity.Seat;
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
	private Long concertId;
	private Long seatId;
	private List<Long> seatIds;
	private LocalDateTime bookingTime;
	private BookingStatus bookingStatus;

}
