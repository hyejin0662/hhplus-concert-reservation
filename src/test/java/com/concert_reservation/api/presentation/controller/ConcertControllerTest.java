package com.concert_reservation.api.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.facade.ConcertFacade;
import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.api.presentation.controller.ConcertController;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ConcertController 테스트")
class ConcertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConcertFacade concertFacade;

	private ConcertRequest concertRequest;
	private ConcertResponse concertResponse;

	@BeforeEach
	@DisplayName("테스트 설정")
	void setUp() {
		concertRequest = new ConcertRequest("Rock Concert");
		concertResponse = new ConcertResponse();
		concertResponse.setConcertId(1L);
		concertResponse.setConcertName("Rock Concert");
		concertResponse.setSeats(Collections.emptyList());
	}

	@Test
	@DisplayName("콘서트 생성 성공 테스트")
	void createConcertSuccessTest() throws Exception {
		// given
		when(concertFacade.createConcert(any(ConcertRequest.class))).thenReturn(concertResponse);

		// when & then
		mockMvc.perform(post("/concerts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"concertName\":\"Rock Concert\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.concertId").value(1L))
			.andExpect(jsonPath("$.data.concertName").value("Rock Concert"));
	}

	@Test
	@DisplayName("콘서트 조회 성공 테스트")
	void getConcertSuccessTest() throws Exception {
		// given
		when(concertFacade.getConcert(anyLong())).thenReturn(concertResponse);

		// when & then
		mockMvc.perform(get("/concerts/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.concertId").value(1L))
			.andExpect(jsonPath("$.data.concertName").value("Rock Concert"));
	}

	@Test
	@DisplayName("콘서트 업데이트 성공 테스트")
	void updateConcertSuccessTest() throws Exception {
		// given
		when(concertFacade.updateConcert(anyLong(), any(ConcertRequest.class))).thenReturn(concertResponse);

		// when & then
		mockMvc.perform(put("/concerts/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"concertName\":\"Rock Concert\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.concertId").value(1L))
			.andExpect(jsonPath("$.data.concertName").value("Rock Concert"));
	}

	@Test
	@DisplayName("콘서트 삭제 성공 테스트")
	void deleteConcertSuccessTest() throws Exception {
		// given
		// No need to mock any response, since it's a void method

		// when & then
		mockMvc.perform(delete("/concerts/1"))
			.andExpect(status().isNoContent());
	}
}
