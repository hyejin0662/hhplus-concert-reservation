package com.concert_reservation.api.application.queue;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.domain.queue.TokenService;
import com.concert_reservation.api.interfaces.controller.queue.dto.TokenRequest;
import com.concert_reservation.api.interfaces.controller.queue.dto.TokenResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenFacade {

	private final TokenService tokenService;

	public TokenResponse getToken(String userId) {
		TokenInfo tokenInfo = tokenService.getWaitingToken(userId);
		return TokenResponse.from(tokenInfo);
	}

	public TokenResponse createToken(TokenRequest tokenRequest) {
		TokenCommand tokenCommand = TokenCommand.builder()
			.userId(tokenRequest.getUserId())
			.build();
		TokenInfo tokenInfo = tokenService.issueToken(tokenCommand);
		return TokenResponse.from(tokenInfo);
	}

	public void transfer() {
		tokenService.activateTokens();
	}

	public TokenResponse.TokenValidateResponse validate(String token) {
		return TokenResponse.TokenValidateResponse.from(tokenService.validateToken(token));
	}


	public void decrementCounter() {
		tokenService.decreaseCounter();
	}
}