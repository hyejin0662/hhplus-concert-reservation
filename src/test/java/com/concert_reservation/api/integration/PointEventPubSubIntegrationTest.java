package com.concert_reservation.api.integration;

import com.concert_reservation.api.domain.point.OutboxRepository;
import com.concert_reservation.api.domain.point.model.OutboxEvent;
import com.concert_reservation.api.domain.point.model.OutboxEventStatus;
import com.concert_reservation.api.interfaces.controller.point.dto.request.PaymentRequest;
import com.concert_reservation.api.interfaces.controller.point.dto.response.PaymentResponse;
import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class PointEventPubSubIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OutboxRepository outboxRepository;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@DisplayName("[Integration] 포인트 결제 이벤트 발행 및 Outbox 검증")
	@Test
	@Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void givenPaymentRequest_whenEventPublished_thenOutboxEventIsPublished() throws Exception {
		// Given
		PaymentRequest request = PaymentRequest.builder()
			.userId("user1")
			.amount(100L)
			.concertOptionId(1L)
			.paymentMethod("CREDIT_CARD")
			.build();

		// When
		var resultActions = mvc.perform(patch("/payments/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk());

		// Then
		String responseContent = resultActions.andReturn().getResponse().getContentAsString();
		WebResponseData<PaymentResponse> response = objectMapper.readValue(responseContent,
			new TypeReference<WebResponseData<PaymentResponse>>() {
			});

		assertThat(response).isNotNull();
		assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
		assertThat(response.getData()).isNotNull();

		TimeUnit.SECONDS.sleep(2);

		String eventKey = response.getData().getPaymentId()  + "-" + response.getData().getUserId();
		Optional<OutboxEvent> outboxEventOptional = outboxRepository.get(eventKey);

		assertThat(outboxEventOptional).isPresent();
		OutboxEvent outboxEvent = outboxEventOptional.get();
		assertThat(outboxEvent.getStatus()).isEqualTo(OutboxEventStatus.PUBLISHED);
	}
}
