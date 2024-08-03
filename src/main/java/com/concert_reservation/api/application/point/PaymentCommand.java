package com.concert_reservation.api.application.point;

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
