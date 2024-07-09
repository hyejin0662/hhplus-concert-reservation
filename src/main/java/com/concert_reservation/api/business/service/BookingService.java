package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;

public interface BookingService {
	BookingResponse bookSeats(BookingRequest bookingRequest);
}
