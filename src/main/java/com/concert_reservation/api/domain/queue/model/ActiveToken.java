package com.concert_reservation.api.domain.queue.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@AllArgsConstructor
public class ActiveToken {

		private String userId;
		private String tokenValue;

	public ActiveToken(String tokenValue) {
		this.tokenValue = tokenValue;
	}
}
