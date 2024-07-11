package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.dto.info.PaymentInfo;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PaymentResponse {
	private Long paymentId;
	private String userId;
	private Long amount;
	private String paymentMethod;
	public static PaymentResponse from(PaymentInfo paymentInfo) {
		return DtoConverter.convert(paymentInfo, PaymentResponse.class);
	}
}
