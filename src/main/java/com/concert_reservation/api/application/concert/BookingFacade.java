package com.concert_reservation.api.application.concert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.interfaces.controller.concert.dto.request.BookingRequest;
import com.concert_reservation.api.interfaces.controller.concert.dto.request.SeatRequest;

import com.concert_reservation.api.interfaces.controller.concert.dto.response.BookingResponse;
import com.concert_reservation.api.interfaces.controller.concert.dto.response.SeatResponse;
import com.concert_reservation.api.domain.concert.BookingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingFacade {

	private final BookingService bookingService;


	public BookingResponse createBooking(BookingRequest bookingRequest) {

		return BookingResponse.from(bookingService.createBooking(bookingRequest.toCommand()));
	}

	public BookingResponse getBooking(String userId) {
		return BookingResponse.from(bookingService.getBooking(userId));
	}


	public void deleteBooking(Long bookingId) {
		bookingService.deleteBooking(bookingId);
	}



	public List<SeatResponse> getAvailableSeats(SeatRequest seatRequest) {
		SeatCommand seatCommand = SeatCommand.builder()
			.concertOptionId(seatRequest.getConcertOptionId())
			.build();
		List<SeatInfo> seatInfos = bookingService.getAvailableSeats(seatCommand);
		return seatInfos.stream()
			.map(SeatResponse::from)
			.collect(Collectors.toList());
	}


	public List<BookingResponse.AvailableDates> getAvailableDates(BookingRequest.AvailableDates seatRequest) {
		return bookingService.getAvailableDates(seatRequest.toCommand())
			.stream()
			.map(BookingResponse.AvailableDates::from)
			.collect(Collectors.toList());
	}

	public void updateAvailableSeat() {
		 bookingService.updateAvailableSeat();

	}


}
