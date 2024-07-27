package com.concert_reservation.api.domain.concert;

import com.concert_reservation.api.domain.concert.ConcertCommand;
import com.concert_reservation.api.domain.concert.ConcertInfo;
import com.concert_reservation.api.domain.concert.Concert;

import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import java.util.List;

import com.concert_reservation.api.domain.concert.ConcertRepository;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

  private final ConcertRepository concertRepository;

  @Override
  public ConcertInfo createConcert(ConcertCommand concertCommand) {
    Concert concert = Concert.builder()
        .concertName(concertCommand.getConcertName())
        .build();
    concertRepository.save(concert);
    return ConcertInfo.from(concert);
  }

  @Override
  public ConcertInfo getConcert(Long concertId) {
    Concert concert = concertRepository.findById(concertId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.CONCERT_NOT_FOUND));
    return ConcertInfo.from(concert);
  }

  @Override
  public ConcertInfo updateConcert(Long concertId, ConcertCommand concertCommand) {
    Concert concert = concertRepository.findById(concertId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.CONCERT_NOT_FOUND));
    concert.setConcertName(concertCommand.getConcertName());
    concertRepository.save(concert);
    return ConcertInfo.from(concert);
  }

  @Override
  public void deleteConcert(Long concertId) {
    concertRepository.deleteById(concertId);
  }

  @Override
  public List<ConcertInfo> getConcerts() {
    List<Concert> concerts = concertRepository.findAll();
    return concerts.stream().map(ConcertInfo::from).collect(Collectors.toList());

  }




}
