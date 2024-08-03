package com.concert_reservation.api.interfaces.controller.queue.dto;

import java.time.LocalDateTime;

import com.concert_reservation.api.application.queue.TokenInfo;
import com.concert_reservation.api.application.queue.TokenValidateInfo;
import com.concert_reservation.api.domain.queue.model.Token;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.TokenStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
	private String tokenValue;

	public static TokenResponse from(TokenInfo token) {
		return DtoConverter.convert(token,TokenResponse.class);
	}

	public Token toEntity() {
		User user = new User();
		user.setUserId(this.userId);

		return Token.builder()
			.tokenId(this.tokenId)
			.user(user)
			.waitingAt(this.waitingAt)
			.expirationAt(this.expirationAt)
			.tokenStatus(this.tokenStatus)
			.build();
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TokenValidateResponse {
		private Long tokenId;
		private String userId;
		private LocalDateTime expirationAt;
		private TokenStatus tokenStatus;
		private String tokenValue;  // redis 사용시
		private boolean isValid; // redis 사용시

		public static TokenValidateResponse from(
			TokenValidateInfo tokenValidateInfo) {
			return DtoConverter.convert(tokenValidateInfo,
				TokenValidateResponse.class);
		}
	}

}
