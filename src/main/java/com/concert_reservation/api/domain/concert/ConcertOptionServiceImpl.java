package com.concert_reservation.api.domain.concert;

import com.concert_reservation.api.domain.concert.ConcertOptionCommand;
import com.concert_reservation.api.domain.concert.ConcertOptionInfo;
import com.concert_reservation.api.domain.concert.ConcertOption;
import com.concert_reservation.api.domain.concert.Seat;
import com.concert_reservation.api.domain.concert.ConcertOptionRepository;
import com.concert_reservation.api.domain.concert.SeatRepository;

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
public class ConcertOptionServiceImpl implements ConcertOptionService {

    private final ConcertOptionRepository concertOptionRepository;
    private final SeatRepository seatRepository;


    @Override
    @Transactional
    public ConcertOptionInfo createConcertOption(ConcertOptionCommand concertOptionCommand) {
        ConcertOption concertOption = ConcertOption.builder()
            .concertId(concertOptionCommand.getConcertId())
            .singerName(concertOptionCommand.getSingerName())
            .concertDate(concertOptionCommand.getConcertDate())
            .capacity(concertOptionCommand.getCapacity())
            .location(concertOptionCommand.getLocation())
            .build();

        List<Seat> seats = IntStream.rangeClosed(1, 50)
            .mapToObj(i -> Seat.builder()
                .seatNumber(i)
                .isReserved(false)
                .price(100)
                .build())
            .collect(Collectors.toList());


        seatRepository.saveAll(seats);
        concertOption.setSeats(seats);
        concertOptionRepository.save(concertOption);

        return ConcertOptionInfo.from(concertOption);
    }


    @Override
    @Transactional(readOnly = true)
    public ConcertOptionInfo getConcertOption(Long concertOptionId) {
        ConcertOption concertOption = concertOptionRepository.findById(concertOptionId)
                .orElseThrow(() -> new CustomException(GlobalResponseCode.INVALID_CONCERT_OPTION));
        return ConcertOptionInfo.from(concertOption);
    }

    @Override
    @Transactional
    public ConcertOptionInfo updateConcertOption(Long concertOptionId, ConcertOptionCommand concertOptionCommand) {
        ConcertOption concertOption = concertOptionRepository.findById(concertOptionId)
                .orElseThrow(() -> new CustomException(GlobalResponseCode.INVALID_CONCERT_OPTION));
        concertOption.setConcertId(concertOptionCommand.getConcertId());
        concertOption.setSingerName(concertOptionCommand.getSingerName());
        concertOption.setConcertDate(concertOptionCommand.getConcertDate());
        concertOption.setCapacity(concertOptionCommand.getCapacity());
        concertOption.setLocation(concertOptionCommand.getLocation());
        concertOptionRepository.save(concertOption);
        return ConcertOptionInfo.from(concertOption);
    }

    @Override
    @Transactional
    public void deleteConcertOption(Long concertOptionId) {
        concertOptionRepository.deleteById(concertOptionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConcertOptionInfo> getConcertOptions() {
        List<ConcertOption> concertOptions = concertOptionRepository.findAll();
        return concertOptions.stream().map(ConcertOptionInfo::from).collect(Collectors.toList());
    }
}
