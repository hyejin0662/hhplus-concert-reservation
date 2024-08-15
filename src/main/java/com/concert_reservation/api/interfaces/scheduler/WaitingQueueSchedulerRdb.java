package com.concert_reservation.api.interfaces.scheduler;// package com.concert_reservation.api.interfaces.scheduler;
//
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// import com.concert_reservation.api.application.facade.TokenFacade;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Component
// @RequiredArgsConstructor
// @Slf4j
// public class WaitingQueueSchedulerRdb {
//
// 	private final TokenFacade tokenFacade;
//
// 	@Scheduled(fixedRate = 1000)// 2초마다
// 	public void transfer() {
// 		tokenFacade.transfer();
// 	}
//
//
//
//
//
//
// 	// @Scheduled(fixedRate = 2000)// 2초마다
// 	@Deprecated
// 	public void scheduledExpireProcessingTokens() {
// 		tokenFacade.scheduledExpireProcessingTokens();
// 	}
//
//
// 	// @Scheduled(fixedRate = 2000)// 2초마다
// 	@Deprecated
// 	public void scheduledExpireWaitingTokens() {
// 		tokenFacade.scheduledExpireWaitingTokens();
// 	}
//
// }