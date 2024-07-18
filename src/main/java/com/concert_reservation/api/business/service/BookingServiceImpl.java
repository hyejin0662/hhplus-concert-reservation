package com.concert_reservation.api.business.service;


import static java.time.LocalDateTime.*;

import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.business.model.dto.command.AvailableDatesCommand;
import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.api.business.model.dto.info.AvailableDatesInfo;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.model.dto.info.ConcertOptionInfo;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.ConcertOption;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.ConcertOptionRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.business.service.BookingService;
import com.concert_reservation.common.type.BookingStatus;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
  private final BookingRepository bookingRepository;
  private final UserRepository userRepository;
  private final SeatRepository seatRepository;
  private final ConcertOptionRepository concertOptionRepository;

  @Override
  @Transactional
  public BookingInfo createBooking(BookingCommand bookingCommand) {
    User user = userRepository.findById(bookingCommand.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Seat seat = seatRepository.findById(bookingCommand.getSeatId())
        .orElseThrow(() -> new RuntimeException("Seat not found"));

    Booking booking = Booking.builder()
        .user(user)
        .seat(seat)
        .bookingTime(bookingCommand.getBookingTime())
        .bookingStatus(BookingStatus.PENDING)
        .build();
    bookingRepository.save(booking);
    return BookingInfo.from(booking);
  }

  @Override
  @Transactional
  public BookingInfo getBooking(String userId) {
    Booking booking = bookingRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Booking not found"));
    return BookingInfo.from(booking);
  }


  @Override
  @Transactional
  public void deleteBooking(Long bookingId) {
    bookingRepository.deleteById(bookingId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SeatInfo> getAvailableSeats(SeatCommand seatCommand) {

    ConcertOption concertOption = concertOptionRepository.findById(seatCommand.getConcertOptionId())
        .orElseThrow(() -> new RuntimeException("ConcertOption not found"));

    return concertOption.getSeats().stream()
        .filter(Seat::isNotReserved)
        .map(SeatInfo::from)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<AvailableDatesInfo> getAvailableDates(AvailableDatesCommand command) {

    List<ConcertOption> concertOptions = concertOptionRepository.findAllByConcertId(command.getConcertId());

    return concertOptions.stream().filter(item->item.isAfter(now())).map(AvailableDatesInfo::from).collect(Collectors.toList());

  }

  @Override
  public BookingInfo confirmBooking(String userId, Long concertOptionId) {
    Booking booking = bookingRepository.findByUserId(userId).orElseThrow();
    booking.doConfirm();
    return BookingInfo.from(booking);
  }
}