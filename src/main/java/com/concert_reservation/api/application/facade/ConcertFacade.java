package com.concert_reservation.api.application.facade;

// import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;
import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.service.impl.BookingServiceImpl;
import com.concert_reservation.api.business.service.impl.SeatServiceImpl;
import com.concert_reservation.api.business.service.impl.UserServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.business.model.dto.command.ConcertCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.service.BookingService;
import com.concert_reservation.api.business.service.ConcertService;
import com.concert_reservation.api.business.service.PointService;
import com.concert_reservation.api.business.service.SeatService;
import com.concert_reservation.api.business.service.TokenService;
import com.concert_reservation.api.business.service.UserService;
import com.concert_reservation.api.business.service.WaitingCountService;
import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

	private final ConcertServiceImpl concertService;
	private final SeatServiceImpl seatService;

	private final BookingServiceImpl bookingService;
	private final UserServiceImpl userService;


	// public List<ConcertResponse> getConcerts(ConcertRequest concertRequest) {
	// 	return concertServiceImpl.getConcerts(concertRequest)
	// 		.stream()
	// 		.map(ConcertResponse::from)
	// 		.collect(Collectors.toList());
	// }



	public ConcertResponse createConcert(ConcertRequest concertRequest) {
		ConcertCommand concertCommand = ConcertCommand.builder()
			.concertName(concertRequest.getConcertName())
			.build();
		ConcertInfo concertInfo = concertService.createConcert(concertCommand);
		return ConcertResponse.from(concertInfo);
	}

	public ConcertResponse getConcert(Long concertId) {
		ConcertInfo concertInfo = concertService.getConcert(concertId);
		return ConcertResponse.from(concertInfo);
	}

	public ConcertResponse updateConcert(Long concertId, ConcertRequest concertRequest) {
		ConcertCommand concertCommand = ConcertCommand.builder()
			.concertId(concertId)
			.concertName(concertRequest.getConcertName())
			.build();
		ConcertInfo concertInfo = concertService.updateConcert(concertId, concertCommand);
		return ConcertResponse.from(concertInfo);
	}

	public void deleteConcert(Long concertId) {
		concertService.deleteConcert(concertId);
	}



	public BookingResponse createBooking(BookingRequest bookingRequest) {
		BookingCommand bookingCommand = BookingCommand.builder()
				.userId(bookingRequest.getUserId())
				.seatId(bookingRequest.getSeatId())
				.bookingTime(bookingRequest.getBookingTime())
				.build();
		BookingInfo bookingInfo = bookingService.createBooking(bookingCommand);
		UserInfo userInfo = userService.getUser(bookingInfo.getUserId());
		return BookingResponse.from(bookingInfo, userInfo);
	}

	public BookingResponse getBooking(Long bookingId) {
		BookingInfo bookingInfo = bookingService.getBooking(bookingId);
		UserInfo userInfo = userService.getUser(bookingInfo.getUserId());
		return BookingResponse.from(bookingInfo, userInfo);
	}

	public BookingResponse updateBooking(Long bookingId, BookingRequest bookingRequest) {
		BookingCommand bookingCommand = BookingCommand.builder()
				.bookingId(bookingId)
				.userId(bookingRequest.getUserId())
				.seatId(bookingRequest.getSeatId())
				.bookingTime(bookingRequest.getBookingTime())
				.build();
		BookingInfo bookingInfo = bookingService.updateBooking(bookingId, bookingCommand);
		UserInfo userInfo = userService.getUser(bookingInfo.getUserId());
		return BookingResponse.from(bookingInfo, userInfo);
	}

	public void deleteBooking(Long bookingId) {
		bookingService.deleteBooking(bookingId);
	}

	public List<SeatResponse> getAvailableSeats(SeatRequest seatRequest) {
		SeatCommand seatCommand = SeatCommand.builder()
				.concertId(seatRequest.getConcertId())
				.date(seatRequest.getDate())
				.build();
		List<SeatInfo> seatInfos = seatService.getAvailableSeats(seatCommand);
		return seatInfos.stream()
				.map(SeatResponse::from)
				.collect(Collectors.toList());
	}
}
