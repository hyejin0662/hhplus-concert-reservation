package com.concert_reservation.api.domain.point;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.domain.point.model.OutboxEvent;

public interface OutboxRepository {
	OutboxEvent save(OutboxEvent outboxEvent);

	Optional<OutboxEvent> get(String eventKey);

	List<OutboxEvent> getUnpublishedEvents();

}
