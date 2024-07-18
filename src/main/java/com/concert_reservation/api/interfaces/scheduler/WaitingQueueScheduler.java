package com.concert_reservation.api.interfaces.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.facade.TokenFacade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WaitingQueueScheduler {

	private final TokenFacade tokenFacade;

	@Scheduled(fixedRate = 2000)// 2초마다
	public void updateTokenStatusToProcessing() {
		tokenFacade.updateTokenStatusToProcessing();
	}


	@Scheduled(fixedRate = 2000)// 2초마다
	public void scheduledExpireProcessingTokens() {
		tokenFacade.scheduledExpireProcessingTokens();
	}


	@Scheduled(fixedRate = 2000)// 2초마다
	public void scheduledExpireWaitingTokens() {
		tokenFacade.scheduledExpireWaitingTokens();
	}

}