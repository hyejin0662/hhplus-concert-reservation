package com.concert_reservation.api.business.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class WaitingToken {
	private String userId;
	private String tokenValue;
	private boolean enqueued;

	public WaitingToken(String tokenValue) {
		this.tokenValue = tokenValue;
	}
}
