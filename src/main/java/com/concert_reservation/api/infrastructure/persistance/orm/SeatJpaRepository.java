package com.concert_reservation.api.infrastructure.persistance.orm;

import java.util.List;

import com.concert_reservation.api.business.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT s FROM Seat s WHERE s.seatId IN :ids AND s.isReserved = true")
	List<Seat> findReservedSeatsBySeatIdIn(List<Long> ids);

	@Query("SELECT s FROM Seat s WHERE s.concert.concertId = :concertId AND s.seatId IN :seatIds AND s.isReserved = :isReserved")
	List<Seat> findSeatsByConcertIdAndSeatIdInAndIsReserved(Long concertId, List<Seat> seatIds, boolean isReserved);
}