package com.concert_reservation.api.infrastructure.persistance.orm;

import com.concert_reservation.api.business.model.entity.TempReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempReservationJpaRepository extends JpaRepository<TempReservation, Long> {
}