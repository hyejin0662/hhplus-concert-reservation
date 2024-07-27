package com.concert_reservation.api.integration;

import com.concert_reservation.api.interfaces.controller.point.dto.PaymentRequest;
import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class PaymentIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }

  @DisplayName("[API][PATCH] 포인트 결제 - 정상 호출")
  @Test
  @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void givenValidRequest_whenPayingWithPoints_thenReturnsOk() throws Exception {
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
    assertThat(response.getData().getUserId()).isEqualTo("user1");
    assertThat(response.getData().getAmount()).isEqualTo(
        900L);  // dummy data에서 userId = userId1 -> 초기값 1000 -> 1000 - 100 = 900
    assertThat(response.getData().getPaymentMethod()).isEqualTo("CREDIT_CARD");
  }

  @DisplayName("[API][PATCH] 포인트 결제 - 실패 호출")
  @Test
  @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void givenInvalidRequest_whenPayingWithPoints_thenThrowsException() throws Exception {
    // Given
    PaymentRequest request = PaymentRequest.builder()
        .userId("invalidUser")
        .amount(100L)
        .concertOptionId(1L)
        .paymentMethod("CREDIT_CARD")
        .build();

    // When & Then
    var resultActions = mvc.perform(patch("/payments/payment")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());

    String responseContent = resultActions.andReturn().getResponse().getContentAsString();

    WebResponseData<Object> errorResponse = objectMapper.readValue(responseContent,
        new TypeReference<WebResponseData<Object>>() {
        });

    assertThat(errorResponse).isNotNull();
    assertThat(errorResponse.getCode()).isEqualTo(GlobalResponseCode.NOT_FOUND_USER_POINT);
    assertThat(errorResponse.getDescription()).isEqualTo(GlobalResponseCode.NOT_FOUND_USER_POINT.getDescription());
  }




  @Test
  @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void 동시에_10건_콘서트_결제시_1건_성공_9건_실패() throws Exception {
    // Given
    int times = 10;  // 동시 요청 수
    String userId = "user1";
    Long amount = 100L;
    Long concertOptionId = 1L;
    String paymentMethod = "CREDIT_CARD";

    PaymentRequest request = PaymentRequest.builder()
        .userId(userId)
        .amount(amount)
        .concertOptionId(concertOptionId)
        .paymentMethod(paymentMethod)
        .build();

    ExecutorService executorService = Executors.newFixedThreadPool(times);
    CountDownLatch latch = new CountDownLatch(times);

    AtomicInteger successCount = new AtomicInteger(0);
    AtomicInteger failCount = new AtomicInteger(0);

    IntStream.range(0, times).forEach(i -> {
      executorService.submit(() -> {
        try {
          mvc.perform(patch("/payments/payment")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
              .andExpect(status().isOk());

          successCount.incrementAndGet();

        } catch (Exception e) {
          failCount.incrementAndGet();
        } finally {
          latch.countDown();
        }
      });
    });

    latch.await(10, TimeUnit.SECONDS);
    executorService.shutdown();

    // Then
    assertThat(successCount.get()).isEqualTo(1);
    assertThat(failCount.get()).isEqualTo(9);
  }


}