package com.concert_reservation.api.domain.point;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.application.point.PaymentCommand;
import com.concert_reservation.api.application.point.PaymentInfo;
import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PointRepository pointRepository;

	/**
	 * 결제 : 낙관락
	 */

	@Transactional
	public PaymentInfo payPoint(PaymentCommand command) {

		Point point = pointRepository.getPoint(command.getUserId()).orElseThrow( () -> new CustomException(GlobalResponseCode.PAYMENT_NOT_AVAILABLE));
		point.pay(command.getAmount());
		pointRepository.save(point);

		return PaymentInfo.from(point);

	}
}
