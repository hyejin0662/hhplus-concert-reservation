package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.PaymentInfo;
import com.concert_reservation.api.business.service.BookingService;
import com.concert_reservation.api.business.service.PaymentService;
import com.concert_reservation.api.business.service.TokenService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentFacade {


	private final PaymentService paymentService;
	private final BookingService bookingService;
	private final TokenService tokenService;



	// todo -> 롤백 로직 구현

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
