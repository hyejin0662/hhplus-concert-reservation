package com.concert_reservation.api.infrastructure.point;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.concert_reservation.api.domain.point.event.PointInternalEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PointKafkaProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void publish(PointInternalEvent pointInternalEvent){
		kafkaTemplate.send(pointInternalEvent.getTopic(), pointInternalEvent.getEventKey(), pointInternalEvent.getPayload());
	}
}
