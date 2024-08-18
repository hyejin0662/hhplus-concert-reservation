package com.concert_reservation.api.domain.common;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.domain.point.OutboxRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxReSender {
	private final OutboxRepository outboxRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void resend() {
		outboxRepository.getUnpublishedEvents()
			.forEach(event -> eventPublisher.publishEvent(OutboxResendInternalEvent.createResendEvent(event)));
	}

}
