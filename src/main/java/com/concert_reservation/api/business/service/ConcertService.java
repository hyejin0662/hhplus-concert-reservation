package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.model.dto.command.ConcertCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;

public interface ConcertService {
	ConcertInfo createConcert(ConcertCommand concertCommand);
	ConcertInfo getConcert(Long concertId);
	ConcertInfo updateConcert(Long concertId, ConcertCommand concertCommand);
	void deleteConcert(Long concertId);

	public ConcertInfo getConcertInfo(Long concertId);
	List<ConcertResponse> getConcerts(ConcertRequest concertRequest);
	// List<SeatResponse> getAvailableSeats(Long concertId);




//	 TokenResponse createWaiting();
// 	ConcertsResponse getConcerts();
// 	List<SeatResponse> getAvailableSeats(LocalDate date, Long concertId);
// 	 BookingResponse bookSeats(TokenRequest tokenRequest, BookingRequest bookingRequest);
// 	PaymentResponse processPayment(String queueToken, PaymentRequest paymentRequest);
// 	UserResponse getUserBalance(long id);
// 	UserResponse chargeUserBalance(UserRequest userRequest);

}
