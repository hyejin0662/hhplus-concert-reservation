package com.concert_reservation.api.business.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.ResponseResult;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl {
  private final ConcertRepository concertRepository;
  private final SeatRepository seatRepository;
  private final BookingRepository bookingRepository;
  private final PointRepository pointRepository;
  private final UserRepository userRepository;

  @Override
  public BookingResponse bookSeats(BookingRequest bookingRequest) {
    Concert concert = concertRepository.findById(bookingRequest.getConcertId())
        .orElseThrow(() -> new IllegalArgumentException("Concert not found"));

    List<Seat> seatsToBook = seatRepository.findByConcertIdAndSeatNumberInAndIsReservedFalse(
        bookingRequest.getConcertId(), bookingRequest.getSeatNumbers());

    if (seatsToBook.size() != bookingRequest.getSeatNumbers().size()) {
      throw new IllegalArgumentException("Some seats are already reserved");
    }

    seatsToBook.forEach(seat -> seat.setIsReserved(true));
    seatRepository.saveAll(seatsToBook);

    User user = userRepository.findById(bookingRequest.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    List<Booking> bookings = seatsToBook.stream()
        .map(seat -> Booking.createBooking(user, seat, LocalDateTime.now()))
        .collect(Collectors.toList());

    bookingRepository.saveAll(bookings);

    List<SeatResponse> seatResponses = seatsToBook.stream()
        .map(SeatResponse::from)
        .collect(Collectors.toList());

    ConcertResponse concertResponse = ConcertResponse.from(concert);
    concertResponse.setSeats(seatResponses);

    UserResponse userResponse = new UserResponse(user.getUserId(), user.getName(), user.getPhoneNumber(),
        user.getEmail(), user.getBalance());

    return BookingResponse.builder()
        .responseResult(ResponseResult.SUCCESS)
        .bookingId(bookings.get(0).getBookingId())
        .bookingStatus(BookingStatus.COMPLETE)
        .bookingTime(bookings.get(0).getBookingTime())
        .user(userResponse)
        .concert(concertResponse)
        .build();
  }
}


