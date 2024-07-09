package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Seat;

public interface SeatRepository {
	List<Seat> findReservedSeatsBySeatIdIn(List<Long> ids);
	void saveAll(List<Seat> seats);

	List<Seat> findSeatsByConcertIdAndSeatIdInAndIsReserved(Long concertId, List<Seat> seatIds, boolean isReserved);

	Optional<Seat> findById(Long seatId);
	List<Seat> findAll();
	Seat save(Seat seat);
	void deleteById(Long seatId);

	List<Seat> findByConcertIdAndIsReservedFalse(Long concertId);
	List<Seat> findByConcertIdAndSeatNumberInAndIsReservedFalse(Long concertId, List<Integer> seatNumbers);

}
