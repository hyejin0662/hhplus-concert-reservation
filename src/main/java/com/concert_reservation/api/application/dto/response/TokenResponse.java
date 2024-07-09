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
	private String concertCode;
	private LocalDateTime waitingAt;
	private LocalDateTime expirationAt;
	private TokenStatus tokenStatus;
	private UserResponse user;

	public static TokenResponse from(Token token) {
		return TokenResponse.builder()
			.tokenId(token.getTokenId())
			.waitingAt(token.getWaitingAt())
			.expirationAt(token.getExpirationAt())
			.tokenStatus(TokenStatus.valueOf(token.getTokenStatus().toString()))
			.user(UserResponse.from(token.getUser()))
			.build();
	}

	public Token toEntity() {
		return Token.builder()
			.tokenId(this.tokenId)
			.waitingAt(this.waitingAt)
			.expirationAt(this.expirationAt)
			.tokenStatus(TokenStatus.valueOf(this.tokenStatus.toString()))
			.user(this.user.toEntity())
			.build();
	}
}