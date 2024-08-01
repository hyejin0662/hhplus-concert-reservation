package com.concert_reservation.api.business.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
