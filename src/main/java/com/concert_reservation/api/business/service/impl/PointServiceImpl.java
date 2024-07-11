package com.concert_reservation.api.business.service.impl;


import java.time.LocalDateTime;


import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.service.PointService;
import com.concert_reservation.common.type.ResponseResult;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

  private final PointRepository pointRepository;

  @Override
  public PointInfo processPayment(Long bookingId) {
    return null;
  }

  @Override
  public PointInfo createPoint(PointCommand pointCommand) {
    Point point = Point.builder()
        .userId(pointCommand.getUserId())
        .balance(pointCommand.getBalance())
        .amount(pointCommand.getAmount())
        .paymentTime(pointCommand.getPaymentTime())
        .paymentMethod(pointCommand.getPaymentMethod())
        .build();
    pointRepository.save(point);
    return PointInfo.from(point);
  }

  @Override
  public PointInfo getPoint(Long pointId) {
    Point point = pointRepository.findById(pointId)
        .orElseThrow(() -> new RuntimeException("Point not found"));
    return PointInfo.from(point);
  }

  @Override
  public PointInfo updatePoint(Long pointId, PointCommand pointCommand) {
    Point point = pointRepository.findById(pointId)
        .orElseThrow(() -> new RuntimeException("Point not found"));
    point.setBalance(pointCommand.getBalance());
    point.setAmount(pointCommand.getAmount());
    point.setPaymentTime(pointCommand.getPaymentTime());
    point.setPaymentMethod(pointCommand.getPaymentMethod());
    pointRepository.save(point);
    return PointInfo.from(point);
  }

  @Override
  public void deletePoint(Long pointId) {
    pointRepository.deleteById(pointId);
  }
  // @Override
  // public PointResponse processPayment(pointRequest pointRequest) {
  //   Booking booking = bookingRepository.findById(pointRequest.getBookingId())
  //       .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
  //
  //   User user = booking.getUser();
  //   Long amount = booking.getSeat().getConcert().getPrice(); // assuming there's a price field in Concert
  //
  //   if (user.getBalance() < amount) {
  //     throw new IllegalArgumentException("Insufficient balance");
  //   }
  //
  //   user.setBalance(user.getBalance() - amount);
  //   userRepository.save(user);
  //
  //   Point point = Point.builder()
  //       .user(user)
  //       .amount(amount)
  //       .balance(user.getBalance())
  //       .paymentTime(LocalDateTime.now())
  //       .paymentMethod("CARD") // assuming a default payment method
  //       .build();
  //
  //   pointRepository.save(point);
  //
  //   BookingResponse bookingResponse = BookingResponse.builder()
  //       .responseResult(ResponseResult.SUCCESS)
  //       .bookingId(booking.getBookingId())
  //       .bookingStatus(booking.getBookingStatus())
  //       .bookingTime(booking.getBookingTime())
  //       .user(new UserResponse(user.getUserId(), user.getName(), user.getBalance()))
  //       .concert(ConcertResponse.from(booking.getSeat().getConcert()))
  //       .build();
  //
  //   return new PointResponse(ResponseResult.SUCCESS, bookingResponse);
  // }
}