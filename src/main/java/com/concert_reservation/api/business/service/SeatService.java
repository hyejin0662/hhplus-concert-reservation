package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;

public interface SeatService {
	SeatResponse createSeat(SeatRequest seatRequest);
	SeatResponse getSeat(Long seatId);
	SeatResponse updateSeat(Long seatId, SeatRequest seatRequest);
	void deleteSeat(Long seatId);
}
