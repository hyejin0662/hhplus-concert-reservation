package com.concert_reservation.api.infrastructure.concert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.concert_reservation.api.domain.concert.model.ConcertOption;

public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOption, Long> {
	List<ConcertOption> findAllByConcertOptionId(Long concertOptionId);
	Optional<ConcertOption> findByConcertOptionIdAndConcertDate(Long concertOptionId, LocalDateTime concertDate);


	@Query("SELECT co FROM ConcertOption co WHERE co.concertOptionId = :concertOptionId AND co.concertDate > :currentDateTime")
	List<ConcertOption> findAvailableDates(@Param("concertOptionId") Long concertOptionId, @Param("currentDateTime") LocalDateTime currentDateTime);

}