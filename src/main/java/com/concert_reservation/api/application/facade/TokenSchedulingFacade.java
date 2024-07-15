package com.concert_reservation.api.application.facade;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.business.service.TokenService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenSchedulingFacade {

	private final TokenService tokenService;

	@Scheduled(fixedRate = 2000)// 2초마다
	public void updateTokenStatusToProcessing() {
		tokenService.scheduledUpdateTokenStatusToProcessing();
	}


	@Scheduled(fixedRate = 2000)// 2초마다
	public void scheduledExpireProcessingTokens() {
		tokenService.scheduledExpireProcessingTokens();
	}


	@Scheduled(fixedRate = 2000)// 2초마다
	public void scheduledExpireWaitingTokens() {
		tokenService.scheduledExpireWaitingTokens();
	}

}
