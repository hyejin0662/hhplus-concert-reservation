package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.facade.PointFacade;
import com.concert_reservation.common.model.WebResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("PointController 단위 테스트")
class PointControllerTest {

	@Mock
	private PointFacade pointFacade;

	@InjectMocks
	private PointController pointController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(pointController).build();
	}

	@Test
	@DisplayName("포인트 충전 성공 테스트")
	void chargePointSuccessTest() throws Exception {
		// given
		PointRequest pointRequest = new PointRequest();
		PointResponse pointResponse = new PointResponse();
		pointResponse.setBalance(1500L);

		when(pointFacade.chargePoint(any(PointRequest.class))).thenReturn(pointResponse);

		// when & then
		mockMvc.perform(patch("/points/charge")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"amount\":500}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.balance", is(1500)));

		verify(pointFacade, times(1)).chargePoint(any(PointRequest.class));
	}

	@Test
	@DisplayName("포인트 차감 성공 테스트")
	void deductPointSuccessTest() throws Exception {
		// given
		PointRequest pointRequest = new PointRequest();
		PointResponse pointResponse = new PointResponse();
		pointResponse.setBalance(500L);

		when(pointFacade.deductPoint(any(PointRequest.class))).thenReturn(pointResponse);

		// when & then
		mockMvc.perform(patch("/points/deduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"amount\":500}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.balance", is(500)));

		verify(pointFacade, times(1)).deductPoint(any(PointRequest.class));
	}

	@Test
	@DisplayName("포인트 조회 성공 테스트")
	void getPointSuccessTest() throws Exception {
		// given
		PointResponse pointResponse = new PointResponse();
		pointResponse.setBalance(1000L);

		when(pointFacade.getPoint(anyLong())).thenReturn(pointResponse);

		// when & then
		mockMvc.perform(get("/points/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.balance", is(1000)));

		verify(pointFacade, times(1)).getPoint(anyLong());
	}
}
