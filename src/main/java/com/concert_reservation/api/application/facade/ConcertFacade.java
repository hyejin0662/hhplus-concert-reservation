package com.concert_reservation.api.application.facade;

// import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

	private final ConcertServiceImpl concertServiceImpl;

	public List<ConcertResponse> getConcerts(ConcertRequest concertRequest) {
		return concertServiceImpl.getConcerts(concertRequest)
			.stream()
			.map(ConcertResponse::from)
			.collect(Collectors.toList());
	}

}
