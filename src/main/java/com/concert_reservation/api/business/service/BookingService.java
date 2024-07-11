package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.business.model.dto.command.AvailableDatesCommand;
import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.api.business.model.dto.info.AvailableDatesInfo;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;

import aj.org.objectweb.asm.commons.Remapper;

public interface BookingService {
	// BookingResponse bookSeats(BookingRequest bookingRequest);

	BookingInfo createBooking(BookingCommand bookingCommand);
	BookingInfo getBooking(String bookingId);
	void deleteBooking(Long bookingId);

	List<SeatInfo> getAvailableSeats(SeatCommand seatCommand);

	List<AvailableDatesInfo> getAvailableDates(AvailableDatesCommand command);

	BookingInfo confirmBooking(String userId, Long concertOptionId);
}
