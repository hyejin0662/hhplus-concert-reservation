package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.ResponseResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.PaymentRequest;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertsResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;

import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
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
  private final SeatRepository seatRepository;
   private final BookingRepository bookingRepository;
   private final PointRepository pointRepository;



  // @Override
  // public List<ConcertResponse> getConcerts() {
  //   List<Concert> concerts = concertRepository.findAll();
  //   return concerts.stream().map(ConcertResponse::from).collect(Collectors.toList());
  // }

  @Override
  public List<ConcertResponse> getConcerts(ConcertRequest concertRequest) {
    // ConcertRequest 필터링 조건을 사용하여 콘서트 목록을 불러오는 로직 구현
    List<Concert> concerts = concertRepository.findConcerts(concertRequest);
    return concerts.stream().map(ConcertResponse::from).collect(Collectors.toList());
  }

  @Override
  public List<SeatResponse> getAvailableSeats(Long concertId) {
    List<Seat> seats = seatRepository.findByConcertIdAndIsReservedFalse(concertId);
    return seats.stream().map(SeatResponse::from).collect(Collectors.toList());
  }
}
