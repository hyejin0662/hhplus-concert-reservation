package com.concert_reservation.api.presentation.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.application.facade.PointFacade;
import com.concert_reservation.api.application.facade.UserFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("UserController 클래스 테스트")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserFacade userFacade;

	private UserRequest userRequest;
	private UserResponse userResponse;

	@InjectMocks
	private PointController pointController;


	@BeforeEach
	@DisplayName("각 테스트 전에 공통 데이터 초기화")
	void setUp() {
		userRequest = new UserRequest();
		userRequest.setUserId("user123");
		userRequest.setName("김삿갓");
		userRequest.setEmail("doe@example.com");
		userRequest.setPhoneNumber("1234567890");
		userRequest.setBalance(1000L);

		userResponse = new UserResponse();
		userResponse.setUserId("user123");
		userResponse.setName("김삿갓");
		userResponse.setEmail("doe@example.com");
		userResponse.setPhoneNumber("1234567890");
		userResponse.setBalance(1000L);
	}





	@Test
	@DisplayName("포인트 충전 성공 테스트")
	void chargePointSuccessTest() throws Exception {
		// given
		PointRequest pointRequest = new PointRequest();
		PointResponse pointResponse = new PointResponse();
		pointResponse.setBalance(1500L);

		when(userFacade.chargePoint(any(PointRequest.class))).thenReturn(pointResponse);

		// when & then
		mockMvc.perform(patch("/points/charge")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"amount\":500}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.balance", is(1500)));

		verify(userFacade, times(1)).chargePoint(any(PointRequest.class));
	}

	@Test
	@DisplayName("포인트 차감 성공 테스트")
	void deductPointSuccessTest() throws Exception {
		// given
		PointRequest pointRequest = new PointRequest();
		PointResponse pointResponse = new PointResponse();
		pointResponse.setBalance(500L);

		when(userFacade.deductPoint(any(PointRequest.class))).thenReturn(pointResponse);

		// when & then
		mockMvc.perform(patch("/points/deduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"amount\":500}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.balance", is(500)));

		verify(userFacade, times(1)).deductPoint(any(PointRequest.class));
	}

	@Test
	@DisplayName("포인트 조회 성공 테스트")
	void getPointSuccessTest() throws Exception {
		// given
		PointResponse pointResponse = new PointResponse();
		pointResponse.setBalance(1000L);

		when(userFacade.getPoint(anyLong())).thenReturn(pointResponse);

		// when & then
		mockMvc.perform(get("/points/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.balance", is(1000)));

		verify(userFacade, times(1)).getPoint(anyLong());
	}

	@Test
	@DisplayName("createUser: 유저 생성 성공 테스트")
	void createUserSuccessTest() throws Exception {
		// given
		when(userFacade.createUser(any(UserRequest.class))).thenReturn(userResponse);

		// when & then
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"name\":\"김삿갓\",\"email\":\"doe@example.com\",\"phoneNumber\":\"1234567890\",\"balance\":1000}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.userId").value("user123"))
			.andExpect(jsonPath("$.data.name").value("김삿갓"))
			.andExpect(jsonPath("$.data.email").value("doe@example.com"))
			.andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
			.andExpect(jsonPath("$.data.balance").value(1000));
	}

	@Test
	@DisplayName("getUser: 유저 조회 성공 테스트")
	void getUserSuccessTest() throws Exception {
		// given
		when(userFacade.getUser(anyString())).thenReturn(userResponse);

		// when & then
		mockMvc.perform(get("/users/user123"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.userId").value("user123"))
			.andExpect(jsonPath("$.data.name").value("김삿갓"))
			.andExpect(jsonPath("$.data.email").value("doe@example.com"))
			.andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
			.andExpect(jsonPath("$.data.balance").value(1000));
	}

	@Test
	@DisplayName("updateUser: 유저 업데이트 성공 테스트")
	void updateUserSuccessTest() throws Exception {
		// given
		when(userFacade.updateUser(anyString(), any(UserRequest.class))).thenReturn(userResponse);

		// when & then
		mockMvc.perform(put("/users/user123")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"김삿갓\",\"email\":\"doe@example.com\",\"phoneNumber\":\"1234567890\",\"balance\":1000}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.userId").value("user123"))
			.andExpect(jsonPath("$.data.name").value("김삿갓"))
			.andExpect(jsonPath("$.data.email").value("doe@example.com"))
			.andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
			.andExpect(jsonPath("$.data.balance").value(1000));
	}

	@Test
	@DisplayName("deleteUser: 유저 삭제 성공 테스트")
	void deleteUserSuccessTest() throws Exception {
		// when & then
		mockMvc.perform(delete("/users/user123"))
			.andExpect(status().isNoContent());
	}
}
