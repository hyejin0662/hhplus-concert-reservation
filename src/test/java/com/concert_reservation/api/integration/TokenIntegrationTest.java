package com.concert_reservation.api.integration;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.application.dto.response.TokenValidateResponse;
import com.concert_reservation.api.application.facade.TokenFacade;
import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.testhelpers.TokenRequestFixture;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.concert_reservation.common.token.QueueTokenGenerator.*;
import static com.concert_reservation.testhelpers.TokenRequestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class TokenIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TokenFacade tokenFacade;
	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@DisplayName("[API][POST] 대기열 진입 및 토큰 조회 - 정상 호출")
	@Test
	void 유효한_요청으로_토큰_생성_시_정상_응답_확인() throws Exception {

		// Given & When
		var resultActions = mvc.perform(post("/api/tokens")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createTokenRequest())))
			.andExpect(status().isOk());

		// Then
		String responseContent = resultActions.andReturn().getResponse().getContentAsString();
		TokenResponse tokenCreateResponse = objectMapper.readValue(responseContent, TokenResponse.class);

		assertThat(tokenCreateResponse).isNotNull();
		assertThat(tokenCreateResponse.getUserId()).isEqualTo("testUser");
		assertThat(tokenCreateResponse.getTokenValue()).isEqualTo(generateToken("testUser"));

		Thread.sleep(200);

		var getTokenActions = mvc.perform(get("/api/tokens")
				.param("userId", "testUser")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		String getTokenResponseContent = getTokenActions.andReturn().getResponse().getContentAsString();
		TokenResponse tokenResponse= objectMapper.readValue(getTokenResponseContent, TokenResponse.class);

		assertThat(tokenResponse).isNotNull();
		assertThat(tokenResponse.getUserId()).isEqualTo("testUser");
		assertThat(tokenResponse.getTokenValue()).isEqualTo(generateToken("testUser"));
	}




	@DisplayName("[API][POST] 대기열 진입 및 처리열 이동 후 validate 테스트 - 정상 호출")
	@Test
	void 대기열에_있는_유저가_처리열로_이동하면_유효성_검사가_성공한다() throws Exception {
		// Given
		TokenRequest tokenRequest = createTokenRequest();

		// When: 유저가 대기열에 추가
		var resultActions = mvc.perform(post("/api/tokens")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tokenRequest)))
			.andExpect(status().isOk());

		String responseContent = resultActions.andReturn().getResponse().getContentAsString();
		TokenResponse tokenCreateResponse = objectMapper.readValue(responseContent, TokenResponse.class);

		Thread.sleep(2000);

		// Then: 처리열에 있는 유저가 validate 요청
		var validateResult = tokenFacade.validate(tokenCreateResponse.getTokenValue());

		// Assertions
		assertThat(validateResult).isNotNull();
		assertThat(validateResult.isValid()).isTrue();
	}


}
