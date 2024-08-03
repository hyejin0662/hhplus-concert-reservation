package com.concert_reservation.api.infrastructure.concert;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.application.concert.BookingInfo;
import com.concert_reservation.api.domain.concert.model.Booking;
import com.concert_reservation.common.type.BookingStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingJpaRepository extends JpaRepository<Booking, Long> {
	Optional<Booking> findByUserUserId(String userId);

	List<BookingInfo> findAllByBookingStatusIs(BookingStatus bookingStatus);

	void deleteByUserUserId(String userId);
}