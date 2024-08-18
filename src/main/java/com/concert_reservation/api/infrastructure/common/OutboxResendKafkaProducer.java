package com.concert_reservation.api.infrastructure.common;

import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.concert_reservation.api.domain.common.OutboxResendInternalEvent;
import com.concert_reservation.api.domain.point.event.PointInternalEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxResendKafkaProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	@EventListener
	public void publish(OutboxResendInternalEvent resendInternalEvent){
		kafkaTemplate.send(resendInternalEvent.getTopic(), resendInternalEvent.getEventKey(), resendInternalEvent.getPayload());
	}
}
