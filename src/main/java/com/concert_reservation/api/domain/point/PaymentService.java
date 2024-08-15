package com.concert_reservation.api.domain.point;

import static com.concert_reservation.api.domain.point.event.PointInternalEvent.*;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.application.point.PaymentCommand;
import com.concert_reservation.api.application.point.PaymentInfo;
import com.concert_reservation.api.domain.point.event.PointInternalEvent;
import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PointRepository pointRepository;

	private final ApplicationEventPublisher eventPublisher;

	/**
	 * 결제 : 낙관락
	 */

	@Transactional
	public PaymentInfo payPoint(PaymentCommand command) {

		Point point = pointRepository.getPoint(command.getUserId()).orElseThrow( () -> new CustomException(GlobalResponseCode.NOT_FOUND_USER_POINT));
		point.use(command.getAmount());
		pointRepository.save(point);

		eventPublisher.publishEvent(createPointInternalEvent(point));
		return PaymentInfo.from(point);

	}

	/**
	 * cancelReason은 enum으로 구현 예정이나 현재는 슈도 코드 개념으로 작성
	 */
	@Transactional
	public void cancelPayment(String userId, String cancelReason) {
		Point point = pointRepository.getPoint(userId).orElseThrow( () -> new CustomException(GlobalResponseCode.NOT_FOUND_USER_POINT));
		point.cancel(cancelReason);
		pointRepository.save(point);
	}
}
