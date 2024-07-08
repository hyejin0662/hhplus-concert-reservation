package com.concert_reservation.api.presentation.controller;


import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.application.dto.request.AvailableSeatsRequest;
import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.PaymentRequest;

import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertsResponse;
import com.concert_reservation.api.application.dto.response.PaymentResponse;

import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.service.ConcertService;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {



	private final ConcertService concertService;

	// @PostMapping("/queue")
	// public QueueResponse queue() {
	// 	return concertService.createQueue();
	// }

	@GetMapping("/concerts")
	public ConcertsResponse concerts() {
		return concertService.getConcerts();
	}

	@GetMapping("/available-seats")
	public List<SeatResponse> getAvailableSeats(@ModelAttribute AvailableSeatsRequest availableSeatsRequest) {
		LocalDate parsedDate = availableSeatsRequest.getDate();
		return concertService.getAvailableSeats(parsedDate, availableSeatsRequest.getConcertId());
	}

	// @PostMapping("/booking")
	// public BookingResponse booking(@RequestHeader("Queue-Token") QueueTokenRequest queueTokenRequest,
	// 	@RequestBody BookingRequest bookingRequest) {
	// 	return concertService.bookSeats(queueTokenRequest, bookingRequest);
	// }

	@PostMapping("/payment")
	public PaymentResponse getPaymentResponse(@RequestHeader("Queue-Token") String queueToken,
		@RequestBody PaymentRequest paymentRequest) {
		// log.info("Payment request received: {}", paymentRequest);
		return concertService.processPayment(queueToken, paymentRequest);
	}

	@GetMapping("/balance/{id}")
	public UserResponse getBalance(@PathVariable long id) {
		return concertService.getUserBalance(id);
	}

	@PostMapping("/balance")
	public UserResponse chargeBalance(@RequestBody UserRequest userRequest) {
		return concertService.chargeUserBalance(userRequest);
	}


}
