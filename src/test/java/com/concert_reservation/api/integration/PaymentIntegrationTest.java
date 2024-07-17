package com.concert_reservation.api.integration;


import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @DisplayName("[API][PATCH] 포인트 결제 - 정상 호출")
  @Test
  @Sql(scripts = "/test-data.sql")
  void givenValidRequest_whenPayingWithPoints_thenReturnsOk() throws Exception {
    // Given
    PaymentRequest request = PaymentRequest.builder()
        .userId("user1")
        .amount(100L)
        .concertOptionId(1L)
        .paymentMethod("Credit Card")
        .build();

    // When
    var resultActions = mvc.perform(patch("/payments/payment")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());

    // Then
    String responseContent = resultActions.andReturn().getResponse().getContentAsString();
    PaymentResponse response = objectMapper.readValue(responseContent, PaymentResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getUserId()).isEqualTo("user1");
    assertThat(response.getAmount()).isEqualTo(100L);
    assertThat(response.getPaymentMethod()).isEqualTo("Credit Card");
  }

  @DisplayName("[API][PATCH] 포인트 결제 - 실패 호출")
  @Test
  @Sql(scripts = "/test-data.sql")
  void givenInvalidRequest_whenPayingWithPoints_thenThrowsException() throws Exception {
    // Given
    PaymentRequest request = PaymentRequest.builder()
        .userId("invalidUser")
        .amount(100L)
        .concertOptionId(1L)
        .paymentMethod("Credit Card")
        .build();

    // When & Then
    assertThatThrownBy(() -> mvc.perform(patch("/payments/payment")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest()))
        .hasCauseInstanceOf(RuntimeException.class)
        .hasMessageContaining(GlobalResponseCode.USER_NOT_FOUND.getDescription());
  }

  @DisplayName("[API][PATCH] 동시성 테스트 - 포인트 결제")
  @Test
  @Sql(scripts = "/test-data.sql")
  void givenConcurrentRequests_whenPayingWithPoints_thenHandlesConcurrencyCorrectly() throws Exception {
    int numberOfThreads = 31; // 30명은 성공하고 1명은 실패해야 함
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);

    for (int i = 1; i <= numberOfThreads; i++) {
      String userId = "user" + i;
      executorService.submit(() -> {
        try {
          PaymentRequest request = PaymentRequest.builder()
              .userId(userId)
              .amount(100L)
              .concertOptionId(1L)
              .paymentMethod("Credit Card")
              .build();
          mvc.perform(patch("/payments/payment")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
              .andExpect(status().isOk());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(); // 모든 스레드가 작업을 마칠 때까지 대기

    // 포인트 결제가 정상적으로 처리된 유저 수를 확인
    var resultActions = mvc.perform(get("/payments")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    String responseContent = resultActions.andReturn().getResponse().getContentAsString();
    PaymentResponse[] responses = objectMapper.readValue(responseContent, PaymentResponse[].class);

    assertThat(responses).hasSize(30);
    assertThat(responses[0].getUserId()).isEqualTo("user1");
    assertThat(responses[0].getAmount()).isEqualTo(100L);
    assertThat(responses[0].getPaymentMethod()).isEqualTo("Credit Card");

    executorService.shutdown();
  }
}