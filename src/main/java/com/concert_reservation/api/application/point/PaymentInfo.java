package com.concert_reservation.api.application.point;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInfo {
	private Long paymentId;
	private String userId;
	private Long amount;
	private LocalDateTime paymentTime;
	private String paymentMethod;

	public static PaymentInfo from(Point point) {
		return PaymentInfo.builder()
			.paymentId(point.getPointId())
			.userId(point.getUser().getUserId())
			.amount(point.getAmount())
			.paymentTime(point.getPaymentTime())
			.paymentMethod(point.getPaymentMethod())
			.build();
	}
}