package com.concert_reservation.api.integration;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.common.model.WebResponseData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConcertIntegrationTest {

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

  @DisplayName("[API][GET] 모든 콘서트 조회 - 정상 호출")
  @Test
  @Sql(scripts = "/concert.sql")
  void givenNothing_whenGettingAllConcerts_thenReturnsAllConcerts() throws Exception {
    var resultActions = mvc.perform(get("/concerts")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    String responseContent = resultActions.andReturn().getResponse().getContentAsString();
    WebResponseData<List<ConcertResponse>> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<List<ConcertResponse>>>() {});

    assertThat(response).isNotNull();
    assertThat(response.getData()).hasSize(2);

    assertThat(response.getData().get(0).getConcertName()).isEqualTo("록 콘서트");
    assertThat(response.getData().get(1).getConcertName()).isEqualTo("재즈 콘서트");
  }

  @DisplayName("[API][POST] 콘서트 생성 - 정상 호출")
  @Test
  @Sql(scripts = "/concert.sql")
  void givenValidRequest_whenCreatingConcert_thenReturnsOk() throws Exception {
    ConcertRequest request = new ConcertRequest();
    request.setConcertName("클래식 콘서트");

    var resultActions = mvc.perform(post("/concerts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());

    String responseContent = resultActions.andReturn().getResponse().getContentAsString();
    ConcertResponse response = objectMapper.readValue(responseContent, ConcertResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getConcertName()).isEqualTo("클래식 콘서트");
  }

  @DisplayName("[API][PUT] 콘서트 수정 - 정상 호출")
  @Test
  @Sql(scripts = "/concert.sql")
  void givenValidRequest_whenUpdatingConcert_thenReturnsOk() throws Exception {
    ConcertRequest request = new ConcertRequest();
    request.setConcertName("업데이트된 콘서트");

    var resultActions = mvc.perform(put("/concerts/{concertId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());

    String responseContent = resultActions.andReturn().getResponse().getContentAsString();
    ConcertResponse response = objectMapper.readValue(responseContent, ConcertResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getConcertName()).isEqualTo("업데이트된 콘서트");
  }

  @DisplayName("[API][DELETE] 콘서트 삭제 - 정상 호출")
  @Test
  @Sql(scripts = "/concert.sql")
  void givenValidRequest_whenDeletingConcert_thenReturnsNoContent() throws Exception {
    mvc.perform(delete("/concerts/{concertId}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @DisplayName("[API][GET] 콘서트 조회 - 정상 호출")
  @Test
  @Sql(scripts = "/concert.sql")
  void givenValidRequest_whenGettingConcert_thenReturnsConcert() throws Exception {
    var resultActions = mvc.perform(get("/concerts/{concertId}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    String responseContent = resultActions.andReturn().getResponse().getContentAsString();
    ConcertResponse response = objectMapper.readValue(responseContent, ConcertResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getConcertName()).isEqualTo("록 콘서트");
  }
}
