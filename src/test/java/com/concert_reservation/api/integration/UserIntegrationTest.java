package com.concert_reservation.api.integration;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/concert.sql")
class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("[API][POST] 사용자 생성 - 정상 호출")
    @Test
    void givenValidRequest_whenCreatingUser_thenReturnsOk() throws Exception {
        // Given
        UserRequest request = new UserRequest("user1", "John Doe", "1234567890", "john.doe@example.com", 1000L);

        // When
        var resultActions = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<UserResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<UserResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getUserId()).isEqualTo("user1");
        assertThat(response.getData().getName()).isEqualTo("John Doe");
    }

    @DisplayName("[API][GET] 사용자 조회 - 정상 호출")
    @Test
    void givenValidUserId_whenGettingUser_thenReturnsOk() throws Exception {
        // Given
        String userId = "user1";

        // When
        var resultActions = mvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<UserResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<UserResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getUserId()).isEqualTo(userId);
    }

    @DisplayName("[API][PUT] 사용자 업데이트 - 정상 호출")
    @Test
    void givenValidRequest_whenUpdatingUser_thenReturnsOk() throws Exception {
        // Given
        String userId = "user1";
        UserRequest request = new UserRequest(userId, "John Smith", "0987654321", "john.smith@example.com", 2000L);

        // When
        var resultActions = mvc.perform(put("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<UserResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<UserResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getUserId()).isEqualTo(userId);
        assertThat(response.getData().getName()).isEqualTo("John Smith");
    }

    @DisplayName("[API][DELETE] 사용자 삭제 - 정상 호출")
    @Test
    void givenValidUserId_whenDeletingUser_thenReturnsNoContent() throws Exception {
        // Given
        String userId = "user1";

        // When
        var resultActions = mvc.perform(delete("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Then
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(204);
    }

    @DisplayName("[API][PATCH] 포인트 충전 - 정상 호출")
    @Test
    void givenValidRequest_whenChargingPoint_thenReturnsOk() throws Exception {
        // Given
        PointRequest request = new PointRequest(1L, "user1", "Credit Card", 1000L, 2000L, LocalDateTime.now());

        // When
        var resultActions = mvc.perform(patch("/users/points/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<PointResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<PointResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getUser().getUserId()).isEqualTo("user1");
        assertThat(response.getData().getAmount()).isEqualTo(1000L);
        assertThat(response.getData().getBalance()).isEqualTo(2000L);
    }

    @DisplayName("[API][GET] 포인트 조회 - 정상 호출")
    @Test
    void givenValidPointId_whenGettingPoint_thenReturnsOk() throws Exception {
        // Given
        Long pointId = 1L;

        // When
        var resultActions = mvc.perform(get("/users/points/{pointId}", pointId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        WebResponseData<PointResponse> response = objectMapper.readValue(responseContent, new TypeReference<WebResponseData<PointResponse>>() {});

        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(GlobalResponseCode.SUCCESS_CODE);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getPointId()).isEqualTo(pointId);
    }
}
