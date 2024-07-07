// package com.concert_reservation.api.presentation.controller.mockApi;
//
// import java.time.LocalDate;
// import java.util.List;
//
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.concert_reservation.api.application.dto.request.AvailableSeatsRequest;
// import com.concert_reservation.api.application.dto.request.BookingRequest;
// import com.concert_reservation.api.application.dto.request.PaymentRequest;
// import com.concert_reservation.api.application.dto.request.TokenRequest;
// import com.concert_reservation.api.application.dto.request.UserRequest;
// import com.concert_reservation.api.application.dto.response.BookingResponse;
// import com.concert_reservation.api.application.dto.response.ConcertsResponse;
// import com.concert_reservation.api.application.dto.response.PaymentResponse;
// import com.concert_reservation.api.application.dto.response.TokenResponse;
// import com.concert_reservation.api.application.dto.response.SeatResponse;
// import com.concert_reservation.api.application.dto.response.UserResponse;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/mock")
// @Slf4j
// public class MockQueueController {
//
// 	private final FakeStore fakeStore;
//
// 	@PostMapping("/queue")
// 	public TokenResponse queue() {
// 		return fakeStore.createFakeQueue();
// 	}
//
// 	@GetMapping("/concerts")
// 	public ConcertsResponse concerts() {
// 		return fakeStore.createConcerts();
// 	}
//
//
//
//
// 	@GetMapping("/available-seats")
// 	public List<SeatResponse> getAvailableSeats(@ModelAttribute AvailableSeatsRequest availableSeatsRequest) {
// 		LocalDate parsedDate = availableSeatsRequest.getDate();
// 		return fakeStore.getAvailableSeatsForDateAndConcert(parsedDate, availableSeatsRequest.getConcertId());
// 	}
//
//
//
// 	@PostMapping("/booking")
// 	public BookingResponse booking(@RequestHeader("Queue-Token") TokenRequest tokenRequest,
// 		@RequestBody BookingRequest bookingRequest) {
// 		return fakeStore.getBookingResponse(tokenRequest, bookingRequest);
// 	}
//
// 	@PostMapping("/payment")
// 	public PaymentResponse getPaymentResponse(@RequestHeader("Queue-Token") String queueToken,
// 		@RequestBody PaymentRequest paymentRequest) {
// 		log.info("Payment request received: {}", paymentRequest);
// 		return fakeStore.getPaymentResponse(queueToken, paymentRequest);
// 	}
// 	@GetMapping("/balance/{id}")
// 	public UserResponse getBalance(@PathVariable long id) {
// 		return fakeStore.getBalance(id);
// 	}
//
// 	@PostMapping("/balance")
// 	public UserResponse chargeBalance(@RequestBody UserRequest userRequest) {
// 		return fakeStore.chargeBalance(userRequest);
// 	}
//
//
// }