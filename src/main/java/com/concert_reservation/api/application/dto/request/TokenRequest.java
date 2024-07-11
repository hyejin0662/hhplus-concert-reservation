package com.concert_reservation.api.application.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
	private String userId;
	private LocalDateTime waitingAt;
	private LocalDateTime expirationAt;
	private String tokenStatus;
	private Long concertId;

}
