package com.concert_reservation.api.interfaces.controller.point.dto;

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
public class PaymentDto {

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Request {
		private String userId;
		private Long amount;
		private Long concertOptionId;
		private String paymentMethod;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Response {
		private Long paymentId;
		private String userId;
		private Long amount;
		private String paymentMethod;
	}
}
