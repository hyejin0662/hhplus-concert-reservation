package com.concert_reservation.api.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.application.facade.TokenFacade;
import com.concert_reservation.common.type.TokenStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TokenController 단위 테스트")
class TokenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TokenFacade tokenFacade;

	private TokenRequest tokenRequest;
	private TokenResponse tokenResponse;

	@BeforeEach
	void setUp() {
		tokenRequest = new TokenRequest();
		tokenRequest.setUserId("user123");
		tokenRequest.setWaitingAt(LocalDateTime.now());
		tokenRequest.setExpirationAt(LocalDateTime.now().plusMinutes(30));
		tokenRequest.setTokenStatus("WAITING");
		tokenRequest.setConcertId(1L);

		tokenResponse = new TokenResponse();
		tokenResponse.setTokenId(1L);
		tokenResponse.setUserId("user123");
		tokenResponse.setWaitingAt(LocalDateTime.now());
		tokenResponse.setExpirationAt(LocalDateTime.now().plusMinutes(30));
		tokenResponse.setTokenStatus(TokenStatus.WAITING);
	}

	@Test
	@DisplayName("토큰 생성 성공 테스트")
	void createTokenSuccessTest() throws Exception {
		// given
		when(tokenFacade.createToken(any(TokenRequest.class))).thenReturn(tokenResponse);

		// when & then
		mockMvc.perform(post("/api/tokens")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"waitingAt\":\"2024-07-12T12:00:00\",\"expirationAt\":\"2024-07-12T12:30:00\",\"tokenStatus\":\"WAITING\",\"concertId\":1}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.tokenId").value(1L))
			.andExpect(jsonPath("$.userId").value("user123"))
			.andExpect(jsonPath("$.tokenStatus").value("WAITING"));
	}

	@Test
	@DisplayName("토큰 조회 성공 테스트")
	void getTokenSuccessTest() throws Exception {
		// given
		when(tokenFacade.getToken(any(String.class))).thenReturn(tokenResponse);

		// when & then
		mockMvc.perform(get("/api/tokens")
				.param("userId", "user123"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.tokenId").value(1L))
			.andExpect(jsonPath("$.userId").value("user123"))
			.andExpect(jsonPath("$.tokenStatus").value("WAITING"));
	}
}
