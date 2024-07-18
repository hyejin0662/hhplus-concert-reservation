package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.concert_reservation.common.type.TokenStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidateResponse {
	private Long tokenId;
	private String userId;
	private LocalDateTime expirationAt;
	private TokenStatus tokenStatus;
	private boolean isValidate;

	public static TokenValidateResponse from(TokenValidateInfo tokenValidateInfo) {
		return DtoConverter.convert(tokenValidateInfo,TokenValidateResponse.class);
	}
}
