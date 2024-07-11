package com.concert_reservation.api.business.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.dto.command.PaymentCommand;
import com.concert_reservation.api.business.model.dto.info.PaymentInfo;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PointRepository pointRepository;

	@Override
	@Transactional
	public PaymentInfo payPoint(PaymentCommand command) {

		Point point = pointRepository.findPointByUserIdOptional(command.getUserId()).orElseThrow();
		point.subtractAmount(command.getAmount());
		pointRepository.save(point);

		return PaymentInfo.from(point);

	}
}
