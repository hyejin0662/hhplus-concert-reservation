package com.concert_reservation.api.interfaces.controller.point.dto.request;

import com.concert_reservation.api.application.point.PaymentCommand;
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
public class PaymentRequest {
	private String userId;
	private Long amount;
	private Long concertOptionId;
	private String paymentMethod;

	public PaymentCommand toCommand() {
		return DtoConverter.convert(this, PaymentCommand.class);
	}
}
