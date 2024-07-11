package com.concert_reservation.api.business.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
import java.util.Optional;

public interface ConcertRepository {
	List<Concert> findAll();

	Optional<Concert> findById(Long concertId);

	Concert save(Concert concert);

	void deleteById(Long concertId);

}
