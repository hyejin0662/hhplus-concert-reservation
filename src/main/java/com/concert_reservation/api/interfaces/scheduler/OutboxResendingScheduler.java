package com.concert_reservation.api.interfaces.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.domain.common.OutboxReSender;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxResendingScheduler {
	private final OutboxReSender outboxReSender;

	@Scheduled(fixedRate = 5000 * 60)
	public void resend(){
		outboxReSender.resend();
	}
}
