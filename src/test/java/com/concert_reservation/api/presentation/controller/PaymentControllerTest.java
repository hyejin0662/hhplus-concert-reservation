package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.facade.PaymentFacade;
import com.concert_reservation.common.model.WebResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("PaymentController 테스트")
class PaymentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PaymentFacade paymentFacade;

	private PaymentRequest paymentRequest;
	private PaymentResponse paymentResponse;

	@BeforeEach
	@DisplayName("테스트 설정")
	void setUp() {
		paymentRequest = new PaymentRequest();
		paymentRequest.setUserId("user123");
		paymentRequest.setAmount(100L);
		paymentRequest.setConcertOptionId(1L);
		paymentRequest.setPaymentMethod("Credit Card");

		paymentResponse = new PaymentResponse();
	}

	@Test
	@DisplayName("포인트 결제 성공 테스트")
	void payPointSuccessTest() throws Exception {
		// given
		when(paymentFacade.payPoint(any(PaymentRequest.class))).thenReturn(paymentResponse);

		// when & then
		mockMvc.perform(patch("/payments")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"amount\":100,\"concertOptionId\":1,\"paymentMethod\":\"Credit Card\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isNotEmpty());
	}
}
