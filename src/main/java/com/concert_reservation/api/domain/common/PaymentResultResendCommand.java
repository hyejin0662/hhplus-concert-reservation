package com.concert_reservation.api.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResultResendCommand {
	private String topic;
	private String eventKey;
	private String payload;

}
