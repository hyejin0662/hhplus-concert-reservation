package com.concert_reservation.api.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.dto.command.PaymentCommand;
import com.concert_reservation.api.business.model.dto.info.PaymentInfo;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.service.PaymentService;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PointRepository pointRepository;

	/**
	 * 결제 : 낙관락
	 */
	@Override
	@Transactional
	public PaymentInfo payPoint(PaymentCommand command) {

		Point point = pointRepository.findPointByUserIdOptional(command.getUserId()).orElseThrow( () -> new CustomException(GlobalResponseCode.PAYMENT_NOT_AVAILABLE));
		point.subtractAmount(command.getAmount());
		pointRepository.save(point);

		return PaymentInfo.from(point);

	}
}
