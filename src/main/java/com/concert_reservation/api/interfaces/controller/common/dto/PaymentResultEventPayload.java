package com.concert_reservation.api.interfaces.controller.common.dto;

import com.concert_reservation.api.domain.common.PaymentResultResendCommand;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResultEventPayload {
	private String topic;
	private String eventKey;
	private String payload;

	public PaymentResultResendCommand toCommand(String eventKey) {
		PaymentResultResendCommand converted = DtoConverter.convert(this, PaymentResultResendCommand.class);
		converted.setEventKey(eventKey);
		return converted;
	}
}
