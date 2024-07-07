package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.common.type.TokenStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
	private Long tokenId;
	private String concertCode;
	private int position;
	private LocalDateTime waitingAt;
	private LocalDateTime expirationAt;
	private TokenStatus tokenStatus;
}