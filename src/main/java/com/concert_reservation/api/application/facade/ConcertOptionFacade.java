package com.concert_reservation.api.application.facade;

import com.concert_reservation.api.application.dto.request.ConcertOptionRequest;
import com.concert_reservation.api.application.dto.response.ConcertOptionResponse;
import com.concert_reservation.api.business.model.dto.command.ConcertOptionCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertOptionInfo;
import com.concert_reservation.api.business.service.ConcertOptionService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertOptionFacade {

    private final ConcertOptionService concertOptionService;

    public ConcertOptionResponse createConcertOption(ConcertOptionRequest concertOptionRequest) {
        ConcertOptionCommand concertOptionCommand = ConcertOptionCommand.builder()
                .concertId(concertOptionRequest.getConcertId())
                .singerName(concertOptionRequest.getSingerName())
                .concertDate(concertOptionRequest.getConcertDate())
                .capacity(concertOptionRequest.getCapacity())
                .location(concertOptionRequest.getLocation())
                .build();
        ConcertOptionInfo concertOptionInfo = concertOptionService.createConcertOption(concertOptionCommand);
        return ConcertOptionResponse.from(concertOptionInfo);
    }

    public ConcertOptionResponse getConcertOption(Long concertOptionId) {
        ConcertOptionInfo concertOptionInfo = concertOptionService.getConcertOption(concertOptionId);
        return ConcertOptionResponse.from(concertOptionInfo);
    }

    public ConcertOptionResponse updateConcertOption(Long concertOptionId, ConcertOptionRequest concertOptionRequest) {
        ConcertOptionCommand concertOptionCommand = ConcertOptionCommand.builder()
                .concertOptionId(concertOptionId)
                .concertId(concertOptionRequest.getConcertId())
                .singerName(concertOptionRequest.getSingerName())
                .concertDate(concertOptionRequest.getConcertDate())
                .capacity(concertOptionRequest.getCapacity())
                .location(concertOptionRequest.getLocation())
                .build();
        ConcertOptionInfo concertOptionInfo = concertOptionService.updateConcertOption(concertOptionId, concertOptionCommand);
        return ConcertOptionResponse.from(concertOptionInfo);
    }

    public void deleteConcertOption(Long concertOptionId) {
        concertOptionService.deleteConcertOption(concertOptionId);
    }

    public List<ConcertOptionResponse> getConcertOptions() {
        List<ConcertOptionInfo> concertOptionInfos = concertOptionService.getConcertOptions();
        return concertOptionInfos.stream().map(ConcertOptionResponse::from).collect(Collectors.toList());
    }
}
