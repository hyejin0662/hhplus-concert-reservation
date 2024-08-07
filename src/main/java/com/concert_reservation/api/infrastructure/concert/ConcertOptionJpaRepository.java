package com.concert_reservation.api.infrastructure.concert;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concert_reservation.api.domain.concert.model.ConcertOption;

public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOption, Long> {
	List<ConcertOption> findAllByConcertOptionId(Long concertOptionId);

}