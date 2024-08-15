package com.concert_reservation.api.domain.point.event;

import static com.concert_reservation.common.mapper.DtoConverter.*;
import static com.concert_reservation.common.topics.PointEventTopic.*;

import com.concert_reservation.api.domain.point.model.OutboxEvent;
import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.api.domain.point.model.OutboxEventStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;`
import lombok.SneakyThrows;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointInternalEvent {

	private String topic;
	private String eventKey;
	private String payload;

	@SneakyThrows
	public static PointInternalEvent createPointInternalEvent(Point point) {
		return PointInternalEvent.builder()
			.eventKey(createEventKey(point))
			.payload(getObjectMapper().writeValueAsString(point))
			.topic(PAYMENT_RESULT.getTopicTitle())
			.build();
	}

	private static String createEventKey(Point point) {
		return point.getPointId() + "-" + point.getUser().getUserId();
	}

	@SneakyThrows
	public OutboxEvent toOutbox() {
		return OutboxEvent.builder()
			.eventType("payment")
			.eventKey(eventKey)
			.payload(payload)
			.topic(topic)
			.status(OutboxEventStatus.INIT)
			.build();
	}
}
