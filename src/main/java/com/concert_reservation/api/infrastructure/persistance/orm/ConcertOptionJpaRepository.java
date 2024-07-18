package com.concert_reservation.api.infrastructure.persistance.orm;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concert_reservation.api.business.model.entity.ConcertOption;
import com.concert_reservation.api.business.model.entity.Seat;

public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOption, Long> {
	List<ConcertOption> findAllByConcertId(Long concertId);

}