package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.common.type.TokenStatus;

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
public class TokenResponse {
	private Long tokenId;
	private String userId;
	private LocalDateTime waitingAt;
	private LocalDateTime expirationAt;
	private TokenStatus tokenStatus;
	private Long waitingCountId;

	public static TokenResponse from(TokenInfo token) {
		return TokenResponse.builder()
			.tokenId(token.getTokenId())
			.userId(token.getUserId())
			.waitingAt(token.getWaitingAt())
			.expirationAt(token.getExpirationAt())
			.tokenStatus(token.getTokenStatus())
			.waitingCountId(token.getWaitingCountId())
			.build();
	}

	public Token toEntity() {
		return Token.builder()
			.tokenId(this.tokenId)
			.userId(this.userId)
			.waitingAt(this.waitingAt)
			.expirationAt(this.expirationAt)
			.tokenStatus(this.tokenStatus)
			.waitingCountId(this.waitingCountId)
			.build();
	}
}
