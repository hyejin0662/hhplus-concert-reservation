package com.concert_reservation.api.business.model.dto.info;

import java.time.LocalDateTime;

import com.concert_reservation.common.type.TokenStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenValidateInfo {
	private Long tokenId;
	private String userId;
	private LocalDateTime expirationAt;
	private TokenStatus tokenStatus;

	private String tokenValue;  // redis 사용시
	private boolean isValid; // redis 사용시

	public static TokenValidateInfo of(String tokenValue, boolean isValid) {
		return TokenValidateInfo.builder().tokenValue(tokenValue).isValid(isValid).build();
	}
}