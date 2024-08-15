package com.concert_reservation.api.infrastructure.common;

import static com.concert_reservation.api.domain.point.model.OutboxEventStatus.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.concert_reservation.api.domain.point.OutboxRepository;
import com.concert_reservation.api.domain.point.model.OutboxEvent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OutboxCoreRepository implements OutboxRepository {
	private final OutboxJpaRepository outboxJpaRepository;

	@Override
	public OutboxEvent save(OutboxEvent outboxEvent) {
		return outboxJpaRepository.save(outboxEvent);
	}

	@Override
	public Optional<OutboxEvent> get(String eventKey) {
		return outboxJpaRepository.findByEventKey(eventKey);
	}


	@Override
	public List<OutboxEvent> getUnpublishedEvents() {
		return outboxJpaRepository.findByStatusAndCreatedAtAfter(INIT, LocalDateTime.now().minusMinutes(5));
	}

}
