package com.concert_reservation.api.application.facade;

import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.application.dto.request.AvailableDatesRequest;
import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.AvailableDatesResponse;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import com.concert_reservation.api.business.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingFacade {

	private final BookingService bookingService;


	public BookingResponse createBooking(BookingRequest bookingRequest) {

		BookingInfo bookingInfo = bookingService.createBooking(bookingRequest.toCommand());
		return BookingResponse.from(bookingInfo);
	}

	public BookingResponse getBooking(String userId) {
		BookingInfo bookingInfo = bookingService.getBooking(userId);
		return BookingResponse.from(bookingInfo);
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


	public List<AvailableDatesResponse> getAvailableDates(AvailableDatesRequest seatRequest) {
		return bookingService.getAvailableDates(seatRequest.toCommand())
			.stream()
			.map(AvailableDatesResponse::from)
			.collect(Collectors.toList());
	}

	public void updateAvailableSeat() {
		 bookingService.updateAvailableSeat();

	}


}
