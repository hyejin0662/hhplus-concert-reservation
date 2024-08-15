package com.concert_reservation.api.domain.concert;

import com.concert_reservation.api.application.concert.ConcertOptionCommand;
import com.concert_reservation.api.application.concert.ConcertOptionInfo;

import com.concert_reservation.api.domain.concert.model.ConcertOption;
import com.concert_reservation.api.domain.concert.model.Seat;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConcertOptionService {

    private final ConcertOptionRepository concertOptionRepository;
    private final SeatRepository seatRepository;



    @Transactional
    public ConcertOptionInfo createConcertOption(ConcertOptionCommand concertOptionCommand) {
        List<Seat> seats = IntStream.rangeClosed(1, 50)
            .mapToObj(i -> Seat.builder()
                .seatNumber(i)
                .isReserved(false)
                .price(100)
                .build())
            .collect(Collectors.toList());

        seatRepository.saveAll(seats);
        return ConcertOptionInfo.from(concertOptionRepository.save(concertOptionCommand.toEntity()));
    }



    @Transactional(readOnly = true)
    public ConcertOptionInfo getConcertOption(Long concertOptionId) {
        ConcertOption concertOption = concertOptionRepository.findById(concertOptionId)
                .orElseThrow(() -> new CustomException(GlobalResponseCode.INVALID_CONCERT_OPTION));
        return ConcertOptionInfo.from(concertOption);
    }


    @Transactional
    public ConcertOptionInfo updateConcertOption(Long concertOptionId, ConcertOptionCommand concertOptionCommand) {
        ConcertOption concertOption = concertOptionRepository.findById(concertOptionId)
                .orElseThrow(() -> new CustomException(GlobalResponseCode.INVALID_CONCERT_OPTION));
        concertOption.updateFromCommand(concertOptionCommand);
        concertOptionRepository.save(concertOption);
        return ConcertOptionInfo.from(concertOption);
    }


    @Transactional
    public void deleteConcertOption(Long concertOptionId) {
        concertOptionRepository.deleteById(concertOptionId);
    }


    @Transactional(readOnly = true)
    public List<ConcertOptionInfo> getConcertOptions() {
        List<ConcertOption> concertOptions = concertOptionRepository.findAll();
        return concertOptions.stream().map(ConcertOptionInfo::from).collect(Collectors.toList());
    }
}
