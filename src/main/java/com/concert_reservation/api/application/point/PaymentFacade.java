package com.concert_reservation.api.application.point;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.concert.BookingPublisher;
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
	private final BookingPublisher bookingPublisher;

	public PaymentResponse payPoint(PaymentRequest paymentRequest) {

		PaymentInfo paymentInfo = null;

		try {
			// 1. 결제 처리
			paymentInfo = paymentService.payPoint(paymentRequest.toCommand());
		} catch (Exception e){
			paymentService.cancelPayment(paymentRequest.getUserId(), "결제 취소 사유 - enum으로 구현");
			throw new RuntimeException("결제 실패");
		}

		// 2. booking 처리
		BookingInfo bookingInfo = bookingService.confirmBooking(paymentRequest.getUserId(), paymentRequest.getConcertOptionId());

		// 3. 토큰 처리 및 기타 데이터 플랫폼 전송을 위한 이벤트 발행
		bookingPublisher.publishBookingCompletedEvent(paymentRequest.getUserId(), paymentRequest.getConcertOptionId());

		// tokenService.expireProcessingTokens(paymentRequest.getUserId());

		return PaymentResponse.from(paymentInfo);
	}

}
