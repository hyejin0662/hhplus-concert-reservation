package com.concert_reservation.api.application.concert;

// import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.interfaces.controller.concert.dto.request.ConcertRequest;
import com.concert_reservation.api.interfaces.controller.concert.dto.response.ConcertResponse;
import com.concert_reservation.api.domain.concert.ConcertService;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

	private final ConcertService concertService;




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

	public List<ConcertResponse> getConcerts() {
		List<ConcertInfo> concertInfos = concertService.getConcerts();
		return concertInfos.stream().map(ConcertResponse::from).collect(Collectors.toList());
	}

}
