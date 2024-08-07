package com.concert_reservation.api.domain.concert;


import static java.time.LocalDateTime.*;

import com.concert_reservation.api.domain.concert.model.Booking;
import com.concert_reservation.api.domain.concert.model.ConcertOption;
import com.concert_reservation.api.domain.concert.model.Seat;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
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



  @Transactional
  public BookingInfo createBooking(BookingCommand bookingCommand) {


    User user = userRepository.getUser(bookingCommand.getUserId())
        .orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));

    // 애초에 찾아올 때 이미 선점이 안된 것만 찾아온다 + EXPIRE가 안된 것만 찾아온다 = 유효한 좌석만 찾는다
    Seat seat = seatRepository.findByIdWithLock(bookingCommand.getSeatId())
        .orElseThrow(() -> new CustomException(GlobalResponseCode.SEAT_NOT_FOUND));

     // 이 내부에서 이미 선점이 되었거나 혹은 만료되었다면 -> 예외 발생
    seat.doReserve();

    Booking booking = Booking.builder()
        .user(user)
        .seat(seat)
        .bookingTime(bookingCommand.getBookingTime())
        .bookingStatus(BookingStatus.PENDING)  // 좌석 임시예약
        .build();
    bookingRepository.save(booking);
    return BookingInfo.from(booking);
  }

  @Transactional
  public BookingInfo getBooking(String userId) {
    Booking booking = bookingRepository.findByUserId(userId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.BOOKING_NOT_FOUND));
    return BookingInfo.from(booking);
  }


  @Transactional
  public void deleteBooking(Long bookingId) {
    bookingRepository.deleteById(bookingId);
  }

  @Transactional(readOnly = true)
  public List<SeatInfo> getAvailableSeats(SeatCommand seatCommand) {

    ConcertOption concertOption = concertOptionRepository.findById(seatCommand.getConcertOptionId())
        .orElseThrow(() -> new CustomException(GlobalResponseCode.INVALID_CONCERT_OPTION));

    return concertOption.getSeats().stream()
        .filter(Seat::isNotReserved)
        .map(SeatInfo::from)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<AvailableDatesInfo> getAvailableDates(AvailableDatesCommand command) {

    List<ConcertOption> concertOptions = concertOptionRepository.findConcertOptions(command.getConcertOptionId());

    return concertOptions.stream().filter(item->item.isAfter(now())).map(AvailableDatesInfo::from).collect(Collectors.toList());

  }

  @Transactional
  public BookingInfo confirmBooking(String userId, Long concertOptionId) {
    Booking booking = bookingRepository.findByUserId(userId).orElseThrow();
    booking.doConfirm();
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