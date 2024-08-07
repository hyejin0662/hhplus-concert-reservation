package com.concert_reservation.api.domain.concert;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.domain.concert.model.ConcertOption;

public interface ConcertOptionRepository {
    Optional<ConcertOption> findById(Long concertOptionId);
    List<ConcertOption> findAll();
    ConcertOption save(ConcertOption concertOption);
    void deleteById(Long concertOptionId);
	List<ConcertOption> findConcertOptions(Long concertOptionId);

}