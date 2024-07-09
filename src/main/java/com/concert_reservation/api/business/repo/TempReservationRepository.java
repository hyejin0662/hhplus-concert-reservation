package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.TempReservation;

public interface TempReservationRepository {

	Optional<TempReservation> findById(Long tempReservationId);
	List<TempReservation> findAll();
	TempReservation save(TempReservation tempReservation);
	void deleteById(Long tempReservationId);

}
