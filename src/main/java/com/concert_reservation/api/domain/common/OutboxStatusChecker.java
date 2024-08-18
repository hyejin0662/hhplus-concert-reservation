package com.concert_reservation.api.domain.common;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.domain.point.OutboxRepository;
import com.concert_reservation.api.domain.point.model.OutboxEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxStatusChecker {
	private final OutboxRepository outboxRepository;

	@Transactional
	public void confirmPublished(PaymentResultResendCommand command) {
		 outboxRepository.get(command.getEventKey())
			 .ifPresent(OutboxEvent::confirmPublished);
	}

}
