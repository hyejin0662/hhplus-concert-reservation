package com.concert_reservation.api.integration;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookingIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("[API][POST] 예약 생성 - 정상 호출")
    @Test
    @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenValidRequest_whenCreatingBooking_thenReturnsOk() throws Exception {
        // Given
        BookingRequest request = BookingRequest.builder()
            .userId("user10")
            .seatId(1L)
            .bookingTime(LocalDateTime.now())
            .build();

        // When
        var resultActions = mvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<BookingResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<BookingResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getUser().getUserId()).isEqualTo("user10");
        assertThat(response.getData().getSeat().getSeatId()).isEqualTo(1L);
    }

    @DisplayName("[API][GET] 예약 조회 - 정상 호출")
    @Test
    @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenValidUserId_whenGettingBooking_thenReturnsOk() throws Exception {
        // Given
        String userId = "user1";

        // When
        var resultActions = mvc.perform(get("/bookings/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<BookingResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<BookingResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getBookingId()).isEqualTo(1L);
    }

    @DisplayName("[API][DELETE] 예약 삭제 - 정상 호출")
    @Test
    @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenValidBookingId_whenDeletingBooking_thenReturnsNoContent() throws Exception {
        // Given
        Long bookingId = 1L;

        // When
        var resultActions = mvc.perform(delete("/bookings/{bookingId}", bookingId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Then
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(204);
    }


    @DisplayName("[API][GET] 사용 가능한 좌석 조회 - 정상 호출")
    @Test
    @Sql(scripts = {"/truncate_tables.sql", "/concert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenValidSeatRequest_whenGettingAvailableSeats_thenReturnsOk() throws Exception {
        // Given
        Long concertOptionId = 1L;

        // When
        var resultActions = mvc.perform(get("/bookings/available-seats")
                .param("concertOptionId", String.valueOf(concertOptionId))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<List<SeatResponse>> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<List<SeatResponse>>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotEmpty();
    }
}
