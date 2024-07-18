package com.concert_reservation.api.application.facade;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.application.dto.response.TokenValidateResponse;
import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenFacade {

	private final TokenService tokenService;

	public TokenResponse getToken(String userId) {
		TokenInfo tokenInfo = tokenService.getToken(userId);
		return TokenResponse.from(tokenInfo);
	}

	public TokenResponse createToken(TokenRequest tokenRequest) {
		TokenCommand tokenCommand = TokenCommand.builder()
			.userId(tokenRequest.getUserId())
			.build();
		TokenInfo tokenInfo = tokenService.createToken(tokenCommand);
		return TokenResponse.from(tokenInfo);


	}

	public void updateTokenStatusToProcessing() {
		tokenService.scheduledUpdateTokenStatusToProcessing();
	}


	public void scheduledExpireProcessingTokens() {
		tokenService.scheduledExpireProcessingTokens();
	}


	public void scheduledExpireWaitingTokens() {
		tokenService.scheduledExpireWaitingTokens();
	}

	public TokenValidateResponse validate(String token) {
		return TokenValidateResponse.from(tokenService.validateToken(token));
	}
}