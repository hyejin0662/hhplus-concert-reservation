package com.concert_reservation.api.application.facade;

// import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;
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

	// public List<ConcertResponse> getConcerts(ConcertRequest concertRequest) {
	// 	return concertServiceImpl.getConcerts(concertRequest)
	// 		.stream()
	// 		.map(ConcertResponse::from)
	// 		.collect(Collectors.toList());
	// }

	// Booking 관련 Facade 메서드

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
}
