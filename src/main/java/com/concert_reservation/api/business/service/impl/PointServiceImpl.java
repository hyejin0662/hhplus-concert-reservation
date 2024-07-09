package com.concert_reservation.api.business.service.impl;


import java.time.LocalDateTime;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.common.type.ResponseResult;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl {
  private final ConcertRepository concertRepository;
  private final SeatRepository seatRepository;
  private final BookingRepository bookingRepository;
  private final PointRepository pointRepository;

  @Override
  public PointResponse processPayment(PaymentRequest PaymentRequest) {
    Booking booking = bookingRepository.findById(PaymentRequest.getBookingId())
        .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

    User user = booking.getUser();
    Long amount = booking.getSeat().getConcert().getPrice(); // assuming there's a price field in Concert

    if (user.getBalance() < amount) {
      throw new IllegalArgumentException("Insufficient balance");
    }

    user.setBalance(user.getBalance() - amount);
    userRepository.save(user);

    Point point = Point.builder()
        .user(user)
        .amount(amount)
        .balance(user.getBalance())
        .paymentTime(LocalDateTime.now())
        .paymentMethod("CARD") // assuming a default payment method
        .build();

    pointRepository.save(point);

    BookingResponse bookingResponse = BookingResponse.builder()
        .responseResult(ResponseResult.SUCCESS)
        .bookingId(booking.getBookingId())
        .bookingStatus(booking.getBookingStatus())
        .bookingTime(booking.getBookingTime())
        .user(new UserResponse(user.getUserId(), user.getName(), user.getBalance()))
        .concert(ConcertResponse.from(booking.getSeat().getConcert()))
        .build();

    return new PointResponse(ResponseResult.SUCCESS, bookingResponse);
  }
}