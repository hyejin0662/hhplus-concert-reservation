package com.concert_reservation.api.domain.common;

import com.concert_reservation.api.domain.point.model.OutboxEvent;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboxResendInternalEvent {

	private String topic;
	private String eventKey;
	private String eventType;
	private String payload;

	public static OutboxResendInternalEvent createResendEvent(OutboxEvent event) {
		return OutboxResendInternalEvent.builder().topic(event.getTopic()).eventKey(event.getEventKey()).eventType(
			event.getEventType()).payload(event.getPayload()).build();
	}
}
