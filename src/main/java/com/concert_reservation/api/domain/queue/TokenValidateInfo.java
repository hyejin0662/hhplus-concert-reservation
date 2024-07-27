package com.concert_reservation.api.domain.queue;

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
}