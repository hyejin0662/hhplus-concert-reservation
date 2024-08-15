package com.concert_reservation.api.domain.common;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.concert_reservation.api.domain.point.OutboxRepository;
import com.concert_reservation.api.domain.point.event.PointInternalEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxSaver {

	private final OutboxRepository outboxRepository;

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void handlePointInternalEvent(PointInternalEvent pointInternalEvent) {
		outboxRepository.save(pointInternalEvent.toOutbox());
	}
}
