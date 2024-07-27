package com.concert_reservation.api.domain.concert;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.domain.concert.Seat;

public interface SeatRepository {

	Optional<Seat> findById(Long seatId);
	List<Seat> findAll();
	Seat save(Seat seat);
	void deleteById(Long seatId);

	Seat findSeat(Long seatId);

	void saveAll(List<Seat> seats);

	Optional<Seat> findByIdWithLock(Long seatId);
}
