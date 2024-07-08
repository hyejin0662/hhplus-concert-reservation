package com.concert_reservation.api.business.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.QueueTokenRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.dto.response.ConcertsResponse;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.QueueResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.service.ConcertService;
import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.QueueStatus;
import com.concert_reservation.common.type.ResponseResult;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

  private final ConcertRepository concertRepository;


  @Override
  public QueueResponse createQueue() {
    LocalDateTime issueDatetime = LocalDateTime.now();
    return new QueueResponse(UUID.randomUUID().toString(), 0,
        issueDatetime, issueDatetime.plusMinutes(5), QueueStatus.PROCESSING);
  }

  @Override
  public ConcertsResponse getConcerts() {
    List<Concert> concerts = concertRepository.findAll();
    List<ConcertResponse> concertResponses = concerts.stream()
        .map(concert -> new ConcertResponse(
            concert.getConcertId(),
            concert.getName(),
            concert.getDate(),
            mapSeats(concert.getSeats())))
        .toList();

    return new ConcertsResponse(concertResponses);
  }

  @Override
  public List<SeatResponse> getAvailableSeats(LocalDate date, Long concertId) {
    List<Seat> availableSeats = concertRepository.findAvailableSeatsByConcertId(concertId, date);
    return mapSeats(availableSeats);
  }

  @Override
  public BookingResponse bookSeats(QueueTokenRequest queueTokenRequest, BookingRequest bookingRequest) {
    // 예약 로직을 구현
    // 예약 정보를 저장하고, 좌석을 예약 상태로 업데이트하는 로직을 구현
    return null;
  }

  @Override
  public PaymentResponse processPayment(String queueToken, PaymentRequest paymentRequest) {
    // 결제 처리 로직을 구현
    // 결제 정보를 저장하고, 예약 상태를 업데이트하는 로직을 구현
    return null;
  }

  @Override
  public UserResponse getUserBalance(long id) {
    // 사용자의 잔액 정보를 가져오는 로직
    return null;
  }

  @Override
  public UserResponse chargeUserBalance(UserRequest userRequest) {
    // 사용자의 잔액을 충전하는 로직.
    return null;
  }

  private List<SeatResponse> mapSeats(List<Seat> seats) {
    return seats.stream()
        .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatNumber(), seat.isReserved()))
        .toList();
  }

}
