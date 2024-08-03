package com.concert_reservation.api.application.point;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.interfaces.controller.point.dto.request.PaymentRequest;
import com.concert_reservation.api.interfaces.controller.point.dto.response.PaymentResponse;
import com.concert_reservation.api.application.concert.BookingInfo;
import com.concert_reservation.api.domain.concert.BookingService;
import com.concert_reservation.api.domain.point.PaymentService;
import com.concert_reservation.api.domain.queue.TokenService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentFacade {


	private final PaymentService paymentService;
	private final BookingService bookingService;
	private final TokenService tokenService;



	public PaymentResponse payPoint(PaymentRequest paymentRequest) {

		// 1. 결제 처리
		PaymentInfo paymentInfo = paymentService.payPoint(paymentRequest.toCommand());

		// 2. booking 처리
		BookingInfo bookingInfo = bookingService.confirmBooking(paymentRequest.getUserId(),
			paymentRequest.getConcertOptionId());

		// 3. 토큰 처리
		tokenService.completeProcessingTokens(paymentRequest.getUserId());


		return PaymentResponse.from(paymentInfo);
	}



}
