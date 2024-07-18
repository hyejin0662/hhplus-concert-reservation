package com.concert_reservation.api.business.service;

import com.concert_reservation.api.business.model.dto.command.ConcertOptionCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertOptionInfo;

import java.util.List;

public interface ConcertOptionService {

    ConcertOptionInfo createConcertOption(ConcertOptionCommand concertOptionCommand);

    ConcertOptionInfo getConcertOption(Long concertOptionId);

    ConcertOptionInfo updateConcertOption(Long concertOptionId, ConcertOptionCommand concertOptionCommand);

    void deleteConcertOption(Long concertOptionId);

    List<ConcertOptionInfo> getConcertOptions();
}
