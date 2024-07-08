package com.concert_reservation.api.business.repo;

import java.util.List;

import com.concert_reservation.api.business.model.entity.Booking;

public interface BookingRepository {

	Booking save(Booking booking);

	int saveAll(List<Booking> bookings);
}
