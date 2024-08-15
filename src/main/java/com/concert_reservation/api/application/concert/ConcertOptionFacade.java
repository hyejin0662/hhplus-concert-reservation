package com.concert_reservation.api.application.concert;

import com.concert_reservation.api.interfaces.controller.concert.dto.ConcertOptionDto;
import com.concert_reservation.api.domain.concert.ConcertOptionService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertOptionFacade {

    private final ConcertOptionService concertOptionService;

    public ConcertOptionDto.Response createConcertOption(ConcertOptionDto.Request concertOptionDto) {
        return ConcertOptionDto.Response.from(concertOptionService.createConcertOption(concertOptionDto.toCommand()));
    }

    public ConcertOptionDto.Response getConcertOption(Long concertOptionId) {
        return ConcertOptionDto.Response.from( concertOptionService.getConcertOption(concertOptionId));
    }

    public ConcertOptionDto.Response updateConcertOption(Long concertOptionId, ConcertOptionDto.Request concertOptionDto) {
        return ConcertOptionDto.Response.from(concertOptionService.updateConcertOption(concertOptionId, concertOptionDto.toCommand()));
    }

    public void deleteConcertOption(Long concertOptionId) {
        concertOptionService.deleteConcertOption(concertOptionId);
    }

    public List<ConcertOptionDto.Response> getConcertOptions() {
        List<ConcertOptionInfo> concertOptionInfos = concertOptionService.getConcertOptions();
        return concertOptionInfos.stream().map(ConcertOptionDto.Response::from).collect(Collectors.toList());
    }
}
