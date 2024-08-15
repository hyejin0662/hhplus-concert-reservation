package com.concert_reservation.api.interfaces.eventhandler;

import static com.concert_reservation.common.mapper.DtoConverter.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.domain.common.OutboxStatusChecker;
import com.concert_reservation.api.interfaces.controller.common.dto.PaymentResultEventPayload;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventListener {

	private final OutboxStatusChecker outboxStatusChecker;

	/**
	 * 셀프 컨슘 이후 outbox 상태 변경 로직
	 */
	@SneakyThrows
	@KafkaListener(topics = "point-result")
	public void consumePointEvent(@Header(KafkaHeaders.RECEIVED_KEY) String key, String message) {
		log.info("Consumed message: {}", message);
		PaymentResultEventPayload paymentResultEventPayload = getObjectMapper().readValue(message, PaymentResultEventPayload.class);
		outboxStatusChecker.confirmPublished(paymentResultEventPayload.toCommand(key));
	}

}
