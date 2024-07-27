package com.concert_reservation.api.domain.concert;

import com.concert_reservation.api.domain.concert.ConcertOption;

import java.util.List;
import java.util.Optional;

public interface ConcertOptionRepository {
    Optional<ConcertOption> findById(Long concertOptionId);
    List<ConcertOption> findAll();
    ConcertOption save(ConcertOption concertOption);
    void deleteById(Long concertOptionId);
	List<ConcertOption> findAllByConcertId(Long concertId);

}