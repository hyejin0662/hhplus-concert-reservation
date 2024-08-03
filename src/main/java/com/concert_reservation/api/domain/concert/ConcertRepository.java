package com.concert_reservation.api.domain.concert;

import java.util.List;

import java.util.Optional;

import com.concert_reservation.api.domain.concert.model.Concert;

public interface ConcertRepository {
	List<Concert> findAll();

	Optional<Concert> getConcert(Long concertId);

	Concert save(Concert concert);

	void deleteById(Long concertId);

}
