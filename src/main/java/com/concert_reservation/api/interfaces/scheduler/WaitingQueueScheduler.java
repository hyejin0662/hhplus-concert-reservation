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



	/**
	 * 대기열에서 처리열로 토큰을 이동시키는 스케줄러 메서드입니다.
	 *
	 * <p>이 메서드는 일정한 주기로 실행되며, 대기열에 있는 토큰을 처리열로 이동시켜
	 * 현재 처리 중인 토큰의 수를 관리합니다.</p>
	 *
	 * <p>처리 과정은 다음과 같습니다:</p>
	 * <ul>
	 *     <li>대기열에 있는 토큰을 주기적으로 확인합니다.</li>
	 *     <li>현재 처리할 수 있는 토큰 수를 계산합니다.</li>
	 *     <li>계산된 수만큼의 토큰을 대기열에서 처리열로 이동시킵니다.</li>
	 * </ul>
	 *
	 * <p>이 메서드는 2초마다 한 번씩 실행됩니다.</p>
	 */
	@Scheduled(fixedRate = 1000)// 2초마다
	public void transfer() {
		tokenFacade.transfer();
	}

}