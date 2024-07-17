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

	List<ConcertInfo> getConcerts();



}
