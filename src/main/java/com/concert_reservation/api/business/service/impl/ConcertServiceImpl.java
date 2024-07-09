package com.concert_reservation.api.business.service.impl;

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
import java.util.Optional;
import java.util.UUID;

import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.PaymentRequest;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertsResponse;
import com.concert_reservation.api.application.dto.response.PaymentResponse;

import com.concert_reservation.api.application.dto.response.TokenResponse;
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


   @Override
   @Transactional
   public BookingResponse bookSeats(TokenRequest tokenRequest, BookingRequest bookingRequest) {
    // 좌석 예약 로직 구현

     var concert = concertRepository.findById(bookingRequest.getConcertId());
     if (concert == null) {
       throw new IllegalArgumentException("해당 콘서트가 존재하지 않습니다.");
     }

     List<Seat> seatsToBook = seatRepository.findSeatsByConcertIdAndSeatIdInAndIsReserved(bookingRequest.getConcertId(),bookingRequest.getSeats(), false);
     if (seatsToBook.size() != bookingRequest.getSeats().size()) {
       throw new IllegalArgumentException("예약 가능한 좌석의 수가 부족합니다.");
     }

     seatsToBook.forEach(seat -> seat.setIsReserved(true));
     seatRepository.saveAll(seatsToBook);

     List<Booking> bookings = seatsToBook.stream()
         .map(seat -> Booking.createBooking(
             new User(bookingRequest.getUserId(), bookingRequest.getUserName(), bookingRequest.getPhoneNumber()), // 예시로 유저 생성
             seat,
             LocalDateTime.now(),
             true
         ))
         .collect(Collectors.toList());

     bookingRepository.saveAll(bookings);

     List<SeatResponse> seatResponses = seatsToBook.stream()
         .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatNumber(), seat.isReserved()))
         .collect(Collectors.toList());

     ConcertResponse concertResponse = new ConcertResponse(
         concert.get().getConcertId(),
         concert.get().getName(),
         concert.get().getDate(),
         seatResponses
     );

     String userId = bookings.get(0).getUser().getUserId();
     User user = bookings.get(0).getUser();
     var balance = pointRepository.findPointsByUserId(userId).getAmount();

     return new BookingResponse(
         ResponseResult.SUCCESS,
         bookings.get(0).getBookingId(),
         BookingStatus.COMPLETE,
         bookings.get(0).getBookingTime(),
         new UserResponse(userId, user.getName(), balance),
         concertResponse
     );
   }

  // @Override
  // public QueueResponse createQueue() {
  //   LocalDateTime issueDatetime = LocalDateTime.now();
  //   return new QueueResponse(UUID.randomUUID().toString(), 0,
  //       issueDatetime, issueDatetime.plusMinutes(5), QueueStatus.PROCESSING);
  // }

  @Override
  public ConcertsResponse getConcerts() {
    return null;
  }

  @Override
  public List<SeatResponse> getAvailableSeats(LocalDate date, Long concertId) {
    return null;
  }

  // @Override
  // public BookingResponse bookSeats(QueueTokenRequest queueTokenRequest, BookingRequest bookingRequest) {
  //   return null;
  // }

  @Override
  public PaymentResponse processPayment(String queueToken, PaymentRequest paymentRequest) {
    return null;
  }

  @Override
  public UserResponse getUserBalance(long id) {
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
