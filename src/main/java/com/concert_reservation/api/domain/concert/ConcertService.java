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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConcertService {

  private final ConcertRepository concertRepository;

  @Transactional
  public ConcertInfo createConcert(ConcertCommand concertCommand) {
    return ConcertInfo.from(concertRepository.save(concertCommand.toEntity()));
  }

  @Transactional(readOnly = true)
  public ConcertInfo getConcert(Long concertId) {
    Concert concert = concertRepository.getConcert(concertId).orElseThrow(() -> new CustomException(GlobalResponseCode.CONCERT_NOT_FOUND));
    return ConcertInfo.from(concert);
  }

  @Transactional
  public ConcertInfo updateConcert(Long concertId, ConcertCommand concertCommand) {
    Concert concert = concertRepository.getConcert(concertId).orElseThrow(() -> new CustomException(GlobalResponseCode.CONCERT_NOT_FOUND));
    return ConcertInfo.from(concertRepository.save(concert.update(concertCommand)));
  }

  @Transactional
  public void deleteConcert(Long concertId) {
    concertRepository.deleteById(concertId);
  }

  @Transactional(readOnly = true)
  public List<ConcertInfo> getConcerts() {
    List<Concert> concerts = concertRepository.findAll();
    return concerts.stream().map(ConcertInfo::from).collect(Collectors.toList());

  }




}
