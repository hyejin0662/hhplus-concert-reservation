package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.common.type.BookingStatus;

public interface BookingRepository {

	Booking save(Booking booking);

	List<Booking> saveAll(List<Booking> bookings);
	Optional<Booking> findById(Long bookingId);
	List<Booking> findAll();
	void deleteById(Long bookingId);

	Optional<Booking> findByUserId(String userId);

	List<BookingInfo> findAll(BookingStatus bookingStatus);

	void deleteByUserId(String userId);
}
