package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Seat;

public interface SeatRepository {

	Optional<Seat> findById(Long seatId);
	List<Seat> findAll();
	Seat save(Seat seat);
	void deleteById(Long seatId);

}
