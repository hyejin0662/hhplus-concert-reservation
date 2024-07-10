package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.business.model.dto.command.ConcertCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.repo.PointRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.service.ConcertService;


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
        .orElseThrow(() -> new RuntimeException("Concert not found"));
    return ConcertInfo.from(concert);
  }

  @Override
  public ConcertInfo updateConcert(Long concertId, ConcertCommand concertCommand) {
    Concert concert = concertRepository.findById(concertId)
        .orElseThrow(() -> new RuntimeException("Concert not found"));
    concert.setConcertName(concertCommand.getConcertName());
    concertRepository.save(concert);
    return ConcertInfo.from(concert);
  }

  @Override
  public void deleteConcert(Long concertId) {
    concertRepository.deleteById(concertId);
  }

  @Override
  public List<ConcertResponse> getConcerts(ConcertRequest concertRequest) {
    // ConcertRequest 필터링 조건을 사용하여 콘서트 목록을 불러오는 로직 구현
    // List<Concert> concerts = concertRepository.findConcerts(concertRequest);
    // return concerts.stream().map(ConcertResponse::from).collect(Collectors.toList());
    return null;
  }

  // @Override
  // public List<SeatResponse> getAvailableSeats(Long concertId) {
  //   LocalDateTime concertDate = LocalDateTime.now();
  //   List<Seat> seats = concertRepository.findAvailableSeatsByConcertIdAndDate(concertId, concertDate);
  //   return seats.stream().map(SeatResponse::from).collect(Collectors.toList());
  // }

  @Override
  public ConcertInfo getConcertInfo(Long concertId) {
    return concertRepository.findById(concertId)
        .map(ConcertInfo::from)
        .orElseThrow(() -> new IllegalArgumentException("Concert not found"));
  }
}
