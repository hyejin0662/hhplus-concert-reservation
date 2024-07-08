package com.concert_reservation.api.business.repo;

import java.util.List;

import com.concert_reservation.api.business.model.entity.Seat;

public interface SeatRepository {
	List<Seat> findReservedSeatsBySeatIdIn(List<Long> ids);
	void saveAll(List<Seat> seats);
}
