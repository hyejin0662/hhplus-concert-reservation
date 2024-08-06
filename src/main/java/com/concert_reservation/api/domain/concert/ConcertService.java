package com.concert_reservation.api.domain.concert;

import com.concert_reservation.api.application.concert.ConcertCommand;
import com.concert_reservation.api.application.concert.ConcertInfo;

import com.concert_reservation.api.domain.concert.model.Concert;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import java.util.List;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertService {

  private final ConcertRepository concertRepository;


  public ConcertInfo createConcert(ConcertCommand concertCommand) {
    Concert concert = Concert.builder()
        .concertName(concertCommand.getConcertName())
        .build();
    concertRepository.save(concert);
    return ConcertInfo.from(concert);
  }


  public ConcertInfo getConcert(Long concertId) {
    Concert concert = concertRepository.getConcert(concertId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.CONCERT_NOT_FOUND));
    return ConcertInfo.from(concert);
  }


  public ConcertInfo updateConcert(Long concertId, ConcertCommand concertCommand) {
    Concert concert = concertRepository.getConcert(concertId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.CONCERT_NOT_FOUND));
    concert.setConcertName(concertCommand.getConcertName());
    concertRepository.save(concert);
    return ConcertInfo.from(concert);
  }


  public void deleteConcert(Long concertId) {
    concertRepository.deleteById(concertId);
  }


  public List<ConcertInfo> getConcerts() {
    List<Concert> concerts = concertRepository.findAll();
    return concerts.stream().map(ConcertInfo::from).collect(Collectors.toList());

  }




}
