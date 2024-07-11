package com.concert_reservation.api.infrastructure.persistance.orm;

import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingJpaRepository extends JpaRepository<Booking, Long> {
	Optional<Booking> findByUserUserId(String userId);

}