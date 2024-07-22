package com.concert_reservation.api.business.service;

import com.concert_reservation.api.business.model.dto.command.ConcertCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.model.entity.Concert;

import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import java.util.List;

import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.service.ConcertService;


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
