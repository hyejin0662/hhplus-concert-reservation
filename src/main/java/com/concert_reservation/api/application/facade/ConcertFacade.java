package com.concert_reservation.api.application.facade;

// import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;
import com.concert_reservation.api.business.service.ConcertService;
import com.concert_reservation.api.business.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.business.model.dto.command.ConcertCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

	private final ConcertService concertService;

	private final UserService userService;



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
