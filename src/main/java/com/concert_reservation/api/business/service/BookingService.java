package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;

public interface BookingService {
	// BookingResponse bookSeats(BookingRequest bookingRequest);

	BookingInfo createBooking(BookingCommand bookingCommand);
	BookingInfo getBooking(Long bookingId);
	BookingInfo updateBooking(Long bookingId, BookingCommand bookingCommand);
	void deleteBooking(Long bookingId);
}
