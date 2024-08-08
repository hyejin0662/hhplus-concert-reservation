package com.concert_reservation.api.domain.concert;


import static com.concert_reservation.common.type.GlobalResponseCode.*;
import static java.time.LocalDateTime.*;

import com.concert_reservation.api.domain.concert.model.Booking;
import com.concert_reservation.api.domain.concert.model.ConcertOption;
import com.concert_reservation.api.domain.concert.model.Seat;
import com.concert_reservation.common.exception.CustomException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.application.concert.AvailableDatesCommand;
import com.concert_reservation.api.application.concert.BookingCommand;
import com.concert_reservation.api.application.concert.SeatCommand;
import com.concert_reservation.api.application.concert.AvailableDatesInfo;
import com.concert_reservation.api.application.concert.BookingInfo;
import com.concert_reservation.api.application.concert.SeatInfo;
import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.api.domain.point.PointRepository;
import com.concert_reservation.api.domain.user.UserRepository;
import com.concert_reservation.common.type.BookingStatus;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {
  private final BookingRepository bookingRepository;
  private final UserRepository userRepository;
  private final SeatRepository seatRepository;
  private final ConcertOptionRepository concertOptionRepository;
  private final PointRepository pointRepository;



  /**
   * 애초에 찾아올 때 이미 선점이 안된 것만 찾아온다
   * 이 내부에서 이미 선점이 되었거나 혹은 만료되었다면 -> 예외 발생
   */
  @Transactional
  public BookingInfo createBooking(BookingCommand bookingCommand) {
    User user = userRepository.getUser(bookingCommand.getUserId()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    Seat seat = seatRepository.getValidSeats(bookingCommand.getSeatId()).orElseThrow(() -> new CustomException(ALREADY_RESERVED));
    seat.reserve();
    return BookingInfo.from(bookingRepository.save(Booking.createBooking(user, seat, bookingCommand)));
  }

  @Transactional(readOnly = true)
  public BookingInfo getBooking(String userId) {
    return BookingInfo.from(bookingRepository.findByUserId(userId).orElseThrow(() -> new CustomException(BOOKING_NOT_FOUND)));
  }

  @Transactional
  public void deleteBooking(Long bookingId) {
    bookingRepository.deleteById(bookingId);
  }

  @Transactional(readOnly = true)
  public List<SeatInfo> getAvailableSeats(SeatCommand seatCommand) {
    return seatRepository.getAvailableSeats(seatCommand.getConcertOptionId()).stream().map(SeatInfo::from).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<AvailableDatesInfo> getAvailableDates(AvailableDatesCommand command) {
    return concertOptionRepository.getAvailableDates(command.getConcertOptionId()).stream().map(AvailableDatesInfo::from).collect(Collectors.toList());
  }

  @Transactional
  public BookingInfo confirmBooking(String userId, Long concertOptionId) {
    Booking booking = bookingRepository.findByUserId(userId).orElseThrow();
    booking.confirm();
    return BookingInfo.from(booking);
  }


  /**
   * 좌석을 계속 점유할 수 있는지 체크 후 상태 변경
   */
  @Transactional
  public void updateAvailableSeat() {
    LocalDateTime nowTime = LocalDateTime.now();

    List<BookingInfo> bookings = bookingRepository.findAll(
        BookingStatus.PENDING); // 임시예약

    bookings.forEach(booking -> {
      LocalDateTime bookingTime = booking.getBookingTime();
      Duration duration = Duration.between(bookingTime, nowTime);

      if (duration.toSeconds() > 5 * 60) {

        booking.cancel();
        Point point = pointRepository.findByUserId(booking.getUserId());
        point.cancel();
        // 좌석 다시 예약 가능 상태로 변경
        Seat seat = seatRepository.findSeat(booking.getSeat().getSeatId());
        seat.isReserved(false);
      }
    });
  }

}