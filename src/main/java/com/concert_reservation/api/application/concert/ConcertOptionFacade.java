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
        ConcertOptionCommand concertOptionCommand = ConcertOptionCommand.builder()
                .concertId(concertOptionDto.getConcertId())
                .singerName(concertOptionDto.getSingerName())
                .concertDate(concertOptionDto.getConcertDate())
                .capacity(concertOptionDto.getCapacity())
                .location(concertOptionDto.getLocation())
                .build();
        ConcertOptionInfo concertOptionInfo = concertOptionService.createConcertOption(concertOptionCommand);
        return ConcertOptionDto.Response.from(concertOptionInfo);
    }

    public ConcertOptionDto.Response getConcertOption(Long concertOptionId) {
        ConcertOptionInfo concertOptionInfo = concertOptionService.getConcertOption(concertOptionId);
        return ConcertOptionDto.Response.from(concertOptionInfo);
    }

    public ConcertOptionDto.Response updateConcertOption(Long concertOptionId, ConcertOptionDto.Request concertOptionDto) {
        ConcertOptionCommand concertOptionCommand = ConcertOptionCommand.builder()
                .concertOptionId(concertOptionId)
                .concertId(concertOptionDto.getConcertId())
                .singerName(concertOptionDto.getSingerName())
                .concertDate(concertOptionDto.getConcertDate())
                .capacity(concertOptionDto.getCapacity())
                .location(concertOptionDto.getLocation())
                .build();
        ConcertOptionInfo concertOptionInfo = concertOptionService.updateConcertOption(concertOptionId, concertOptionCommand);
        return ConcertOptionDto.Response.from(concertOptionInfo);
    }

    public void deleteConcertOption(Long concertOptionId) {
        concertOptionService.deleteConcertOption(concertOptionId);
    }

    public List<ConcertOptionDto.Response> getConcertOptions() {
        List<ConcertOptionInfo> concertOptionInfos = concertOptionService.getConcertOptions();
        return concertOptionInfos.stream().map(ConcertOptionDto.Response::from).collect(Collectors.toList());
    }
}
