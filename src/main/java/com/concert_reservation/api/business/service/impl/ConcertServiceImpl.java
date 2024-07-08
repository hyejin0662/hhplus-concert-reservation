// package com.concert_reservation.api.business.service.impl;
//
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.UUID;
//
// import org.springframework.transaction.annotation.Transactional;
// import com.concert_reservation.api.application.dto.request.BookingRequest;
// import com.concert_reservation.api.application.dto.request.PaymentRequest;
// import com.concert_reservation.api.application.dto.request.QueueTokenRequest;
// import com.concert_reservation.api.application.dto.request.TokenRequest;
// import com.concert_reservation.api.application.dto.request.UserRequest;
// import com.concert_reservation.api.application.dto.response.BookingResponse;
// import com.concert_reservation.api.application.dto.response.ConcertsResponse;
// import com.concert_reservation.api.application.dto.response.PaymentResponse;
// import com.concert_reservation.api.application.dto.response.QueueResponse;
// import com.concert_reservation.api.application.dto.response.TokenResponse;
// import com.concert_reservation.api.application.dto.response.SeatResponse;
// import com.concert_reservation.api.application.dto.response.UserResponse;
// import com.concert_reservation.api.business.model.entity.Seat;
// import com.concert_reservation.api.business.repo.ConcertRepository;
// import com.concert_reservation.api.business.repo.BookingRepository;
// import com.concert_reservation.api.business.repo.SeatRepository;
// import com.concert_reservation.api.business.service.ConcertService;
// import com.concert_reservation.common.type.QueueStatus;
//
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
//
// @Service
// @RequiredArgsConstructor
// public class ConcertServiceImpl implements ConcertService {
//
//   private final ConcertRepository concertRepository;
//   private final SeatRepository seatRepository;
//   // private final BookingRepository bookingRepository;
//
//
//   @Override
//   @Transactional
//   public BookingResponse bookSeats(TokenRequest tokenRequest, BookingRequest bookingRequest) {
//     // 좌석 예약 로직 구현
//     // Concert concert = concertRepository.findById(bookingRequest.getConcertOptionId());
//     // if (concert == null) {
//     //   throw new IllegalArgumentException("Concert not found");
//     // }
//     //
//     // List<Seat> seatsToBook = seatRepository.findByIdInAndReserved(bookingRequest.getSeats(), false);
//     // if (seatsToBook.size() != bookingRequest.getSeats().size()) {
//     //   throw new IllegalArgumentException("Some seats are already reserved");
//     // }
//     //
//     // seatsToBook.forEach(seat -> seat.setIsReserved(true));
//     // seatRepository.saveAll(seatsToBook);
//     //
//     // List<Booking> bookings = seatsToBook.stream()
//     //     .map(seat -> Booking.createBooking(
//     //         new User(bookingRequest.getUserId(), "UserA", "1000"), // 예시로 유저 생성
//     //         seat,
//     //         Timestamp.valueOf(LocalDateTime.now()),
//     //         true
//     //     ))
//     //     .collect(Collectors.toList());
//     //
//     // bookingRepository.saveAll(bookings);
//     //
//     // List<SeatResponse> seatResponses = seatsToBook.stream()
//     //     .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatNumber(), seat.isReserved()))
//     //     .collect(Collectors.toList());
//     //
//     // ConcertResponse concertResponse = new ConcertResponse(
//     //     concert.getConcertId(),
//     //     concert.getName(),
//     //     concert.getDate(),
//     //     seatResponses
//     // );
//     //
//     // String userId = bookings.get(0).getUser().getUserId();
//     // User user = bookings.get(0).getUser();
//     // var balance = pointService.getPointsByUserId(userId).getAmount();
//     //
//     // return new BookingResponse(
//     //     ResponseResult.SUCCESS,
//     //     bookings.get(0).getBookingId(),
//     //     BookingStatus.COMPLETE,
//     //     bookings.get(0).getBookingTime().toLocalDateTime(),
//     //     new UserResponse(userId, user.getName(), balance),
//     //     concertResponse
//     // );
//     return null;
//   }
//
//   @Override
//   public QueueResponse createQueue() {
//     LocalDateTime issueDatetime = LocalDateTime.now();
//     return new QueueResponse(UUID.randomUUID().toString(), 0,
//         issueDatetime, issueDatetime.plusMinutes(5), QueueStatus.PROCESSING);
//   }
//
//   @Override
//   public ConcertsResponse getConcerts() {
//     return null;
//   }
//
//   @Override
//   public List<SeatResponse> getAvailableSeats(LocalDate date, Long concertId) {
//     return null;
//   }
//
//   // @Override
//   // public BookingResponse bookSeats(QueueTokenRequest queueTokenRequest, BookingRequest bookingRequest) {
//   //   return null;
//   // }
//
//   @Override
//   public PaymentResponse processPayment(String queueToken, PaymentRequest paymentRequest) {
//     return null;
//   }
//
//   @Override
//   public UserResponse getUserBalance(long id) {
//     return null;
//   }
//
//   @Override
//   public UserResponse chargeUserBalance(UserRequest userRequest) {
//     // 사용자의 잔액을 충전하는 로직.
//     return null;
//   }
//
//   private List<SeatResponse> mapSeats(List<Seat> seats) {
//     return null;
//     // return seats.stream()
//     //     .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatNumber(), seat.isReserved()))
//     //     .toList();
//   }
//
//
// }
