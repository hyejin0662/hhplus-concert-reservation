package com.concert_reservation.api.infrastructure.common;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concert_reservation.api.domain.point.model.OutboxEvent;
import com.concert_reservation.api.domain.point.model.OutboxEventStatus;

public interface OutboxJpaRepository extends JpaRepository<OutboxEvent, Long> {

	Optional<OutboxEvent> findByEventKey(String key);

	List<OutboxEvent> findByStatusAndCreatedAtAfter(OutboxEventStatus status, LocalDateTime timestamp);
}
