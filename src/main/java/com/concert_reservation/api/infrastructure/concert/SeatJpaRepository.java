package com.concert_reservation.api.infrastructure.concert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.domain.concert.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

	Seat findSeatBySeatId(Long seatId);

	// todo -> native query pessimistic lock으로 작성
	// 비관적 락을 사용하여 좌석을 조회하는 네이티브 쿼리
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "SELECT s FROM Seat s WHERE s.seatId = :seatId")
	Optional<Seat> findByIdWithLock(@Param("seatId") Long seatId);




	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "SELECT s FROM Seat s WHERE s.seatId = :seatId AND s.isReserved = false")
	Optional<Seat> findByIdWithLockAndNotReserved(@Param("seatId") Long seatId);

	List<Seat> findByConcertOptionConcertOptionIdAndIsReservedFalse(Long concertOptionId);
}