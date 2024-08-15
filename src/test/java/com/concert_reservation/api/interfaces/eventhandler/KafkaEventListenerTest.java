package com.concert_reservation.api.interfaces.eventhandler;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import com.concert_reservation.api.domain.point.event.PointInternalEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092"}, ports = {9092}, topics = {"point-result"})
class KafkaEventListenerTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("PointInternalEvent 발행 및 소비 테스트")
	void testConsumePointEvent() throws Exception {
		// Given
		PointInternalEvent event = PointInternalEvent.builder()
			.eventKey("point-key-123")
			.payload("""
				{
					"point_id": 1,
					"user": {
						"user_id": "1",
						"name": "name1",
						"email": "user1@example.com",
						"phone_number": "010-1234-0001"
					},
					"amount": 47,
					"payment_time": "2024-08-08T23:54:19",
					"payment_method": "CREDIT_CARD",
					"version": 8
				}			
				""")
			.topic("point-result")
			.build();

		String payload = objectMapper.writeValueAsString(event);

		// When
		kafkaTemplate.send("point-result", event.getEventKey(), payload);

		// Then: Kafka로부터 이벤트를 소비하고, 게시된 이벤트와 동일한지 검증

		// Kafka 소비자 설정을 초기화하고, 주제를 "point-result"로 설정
		Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafkaBroker);
		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

		DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new StringDeserializer());
		var consumer = consumerFactory.createConsumer();
		consumer.subscribe(java.util.List.of("point-result"));

		// Kafka로부터 단일 레코드를 가져오는 부분
		ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(consumer, "point-result");

		// 받은 메시지를 PointInternalEvent 객체로 역직렬화하고, 원래 이벤트와 비교하여 동일함을 확인하는 부분
		PointInternalEvent receivedEvent = objectMapper.readValue(received.value(), PointInternalEvent.class);
		assertThat(receivedEvent).isEqualToComparingFieldByField(event);

		consumer.close();
	}
}
