package com.concert_reservation.api.business.model.dto.command;

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
public class PaymentCommand {
	private String userId;
	private Long amount;
	private Long concertOptionId;
	private String paymentMethod;

}
