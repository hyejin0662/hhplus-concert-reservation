// package com.concert_reservation.api.presentation.controller.mockApi;
//
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;
//
// import org.springframework.stereotype.Component;
//
// import com.concert_reservation.api.application.dto.request.BookingRequest;
// import com.concert_reservation.api.application.dto.request.PaymentRequest;
// import com.concert_reservation.api.application.dto.request.TokenRequest;
// import com.concert_reservation.api.application.dto.request.UserRequest;
// import com.concert_reservation.api.application.dto.response.BookingResponse;
// import com.concert_reservation.api.application.dto.response.ConcertResponse;
// import com.concert_reservation.api.application.dto.response.ConcertsResponse;
// import com.concert_reservation.api.application.dto.response.PaymentResponse;
// import com.concert_reservation.api.application.dto.response.TokenResponse;
// import com.concert_reservation.api.application.dto.response.SeatResponse;
// import com.concert_reservation.api.application.dto.response.UserResponse;
// import com.concert_reservation.common.type.BookingStatus;
// import com.concert_reservation.common.type.TokenStatus;
// import com.concert_reservation.common.type.ResponseResult;
//
// @Component
// public class FakeStore {
//
//     public ConcertsResponse createConcerts() {
//
//         List<ConcertResponse> concertsResponses = new ArrayList<>(List.of(
//                 new ConcertResponse(1L, "concertA", LocalDateTime.now().plusDays(1), createSeats(1)),
//                 new ConcertResponse(2L, "concertB", LocalDateTime.now().plusDays(2), createSeats(2)),
//                 new ConcertResponse(3L, "concertC", LocalDateTime.now().plusDays(2), createSeats(3))
//         ));
//         return new ConcertsResponse(concertsResponses);
//     }
//
//     private static List<SeatResponse> createSeats(int batchNumber) {
//         List<SeatResponse> seats = new ArrayList<>();
//         final int seatsPerBatch = 50;
//         int firstSeatNumber = (batchNumber - 1) * seatsPerBatch + 1;
//
//         for (int i = 0; i < seatsPerBatch; i++) {
//             seats.add(new SeatResponse((long)(firstSeatNumber + i - 1), firstSeatNumber + i, false));
//         }
//
//         return seats;
//     }
//
//
//     public TokenResponse createFakeQueue() {
//         LocalDateTime issueDatetime = LocalDateTime.now();
//         return new TokenResponse(UUID.randomUUID().toString(), 0,
//                 issueDatetime, issueDatetime.plusMinutes(5), TokenStatus.PROCESSING);
//     }
//
//     public BookingResponse getBookingResponse(TokenRequest tokenRequest, BookingRequest bookingRequest) {
//         // 좌석 정보
//         List<SeatResponse> seatResponse = new ArrayList<>();
//         String[] seats = bookingRequest.getSeats().split(",");
//         for (int i = 0; i<seats.length; i++) {
//             seatResponse.add(new SeatResponse((long) i, Integer.parseInt(seats[i].split("_")[1]), true));
//         }
//         return new BookingResponse(
//                 ResponseResult.SUCCESS,
//                 1L,
//                 BookingStatus.COMPLETE,
//                 LocalDateTime.now(),
//                 new UserResponse(bookingRequest.getUserId(), "UserA", 100000),
//                 new ConcertResponse(bookingRequest.getConcertOptionId(),"concertA",LocalDateTime.now().plusDays(5), seatResponse)
//                 );
//     }
//
//     public PaymentResponse getPaymentResponse(String queueToken, PaymentRequest paymentRequest) {
//         List<SeatResponse> seatResponse = new ArrayList<>();
//         String[] seats = paymentRequest.getSeats().split(",");
//         for (int i = 0; i < seats.length; i++) {
//             seatResponse.add(new SeatResponse((long) i, Integer.parseInt(seats[i].split("_")[1]), true));
//         }
//         BookingResponse bookingResponse = new BookingResponse(
//             ResponseResult.SUCCESS,
//             paymentRequest.getBookingId(),
//             BookingStatus.COMPLETE,
//             LocalDateTime.now(),
//             new UserResponse(paymentRequest.getUserId(), "UserA", 100000),
//             new ConcertResponse(paymentRequest.getConcertId(), "concertA", LocalDateTime.now().plusDays(5), seatResponse)
//         );
//         return new PaymentResponse(ResponseResult.SUCCESS, bookingResponse);
//     }
//
//     public UserResponse getBalance(long id) {
//         return new UserResponse(id, "아무개", 10000);
//     }
//
//     public UserResponse chargeBalance(UserRequest userRequest) {
//         return new UserResponse(userRequest.getUserId(), "아무개", userRequest.getBalance());
//     }
//
//     public List<SeatResponse> getAvailableSeatsForDateAndConcert(LocalDate date, Long concertId) {
//         List<SeatResponse> seats = new ArrayList<>();
//         // 특정 날짜 및 콘서트 ID의 예약 가능한 좌석 목록을 생성하는 로직
//         if (concertId == 1L) {
//             seats = createSeats(1);
//         } else if (concertId == 2L) {
//             seats = createSeats(2);
//         }
//         // 모든 좌석이 예약되지 않은 것으로 설정
//         seats.forEach(seat -> seat.setReserved(false));
//         return seats;
//     }
// }
