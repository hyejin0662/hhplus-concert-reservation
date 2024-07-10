package com.concert_reservation.api.business.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.business.service.BookingService;
import com.concert_reservation.common.type.BookingStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
  private final BookingRepository bookingRepository;

  @Override
  public BookingInfo createBooking(BookingCommand bookingCommand) {
    Booking booking = Booking.createBooking(
        bookingCommand.getUserId(),
        bookingCommand.getSeatId(),
        bookingCommand.getBookingTime(),
        bookingCommand.getBookingStatus()
    );
    bookingRepository.save(booking);
    return BookingInfo.from(booking);
  }

  @Override
  public BookingInfo getBooking(Long bookingId) {
    Booking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new RuntimeException("Booking not found"));
    return BookingInfo.from(booking);
  }

  @Override
  public BookingInfo updateBooking(Long bookingId, BookingCommand bookingCommand) {
    Booking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new RuntimeException("Booking not found"));
    booking.updateBookingStatus(bookingCommand.getBookingStatus());
    bookingRepository.save(booking);
    return BookingInfo.from(booking);
  }

  @Override
  public void deleteBooking(Long bookingId) {
    bookingRepository.deleteById(bookingId);
  }

}


