package com.concert_reservation.api.business.service;

import java.time.LocalDate;
import java.util.List;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertsResponse;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;

public interface ConcertService {

	// QueueResponse createQueue();
	ConcertsResponse getConcerts();
	List<SeatResponse> getAvailableSeats(LocalDate date, Long concertId);
	// BookingResponse bookSeats(QueueTokenRequest queueTokenRequest, BookingRequest bookingRequest);
	PaymentResponse processPayment(String queueToken, PaymentRequest paymentRequest);
	UserResponse getUserBalance(long id);
	UserResponse chargeUserBalance(UserRequest userRequest);

}
