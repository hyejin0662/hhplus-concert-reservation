package com.concert_reservation.api.business.model.dto.info;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Point;

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