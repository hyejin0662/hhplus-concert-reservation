package com.concert_reservation.api.interfaces.controller;

import com.concert_reservation.api.application.dto.request.AvailableDatesRequest;
import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.AvailableDatesResponse;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.application.facade.BookingFacade;
import com.concert_reservation.common.type.BookingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BookingControllerTest 단위 테스트")
class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingFacade bookingFacade;

	private BookingRequest bookingRequest;
	private BookingResponse bookingResponse;

	@BeforeEach
	void setUp() {
		bookingRequest = new BookingRequest("user123", 1L, 1L, LocalDateTime.now());
		bookingResponse = new BookingResponse();
		bookingResponse.setBookingId(1L);
		bookingResponse.setBookingStatus(BookingStatus.CONFIRMED);
		bookingResponse.setBookingTime(LocalDateTime.now());
		bookingResponse.setUser(new UserResponse("user123", "John Doe", "john.doe@example.com", "123-456-7890", 1000L));
		bookingResponse.setSeat(new SeatResponse(1L, 1, false, new ConcertResponse(1L, "Concert Name", null)));
	}

	@Test
	@DisplayName("예약 생성 성공 테스트")
	void createBookingSuccessTest() throws Exception {
		// given
		when(bookingFacade.createBooking(any(BookingRequest.class))).thenReturn(bookingResponse);

		// when & then
		mockMvc.perform(post("/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"user123\",\"concertOptionId\":1,\"seatId\":1,\"bookingTime\":\"2023-07-12T12:00:00\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.bookingId").value(1L))
			.andExpect(jsonPath("$.data.bookingStatus").value("CONFIRMED"));
	}

	@Test
	@DisplayName("예약 조회 성공 테스트")
	void getBookingSuccessTest() throws Exception {
		// given
		when(bookingFacade.getBooking(anyString())).thenReturn(bookingResponse);

		// when & then
		mockMvc.perform(get("/bookings/user123"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.bookingId").value(1L))
			.andExpect(jsonPath("$.data.bookingStatus").value("CONFIRMED"));
	}

	@Test
	@DisplayName("예약 삭제 성공 테스트")
	void deleteBookingSuccessTest() throws Exception {
		// given

		// when & then
		mockMvc.perform(delete("/bookings/1"))
			.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("예약 가능한 날짜 조회 성공 테스트")
	void getAvailableDatesSuccessTest() throws Exception {
		// given
		AvailableDatesResponse availableDatesResponse = new AvailableDatesResponse();
		availableDatesResponse.setConcertOptionId(1L);
		availableDatesResponse.setConcertId(1L);
		availableDatesResponse.setSingerName("Singer");
		availableDatesResponse.setConcertDate(LocalDateTime.now());
		availableDatesResponse.setCapacity(100L);
		availableDatesResponse.setLocation("Location");

		when(bookingFacade.getAvailableDates(any(AvailableDatesRequest.class))).thenReturn(Collections.singletonList(availableDatesResponse));

		// when & then
		mockMvc.perform(get("/bookings/available-dates")
				.param("concertOptionId", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].concertOptionId").value(1L))
			.andExpect(jsonPath("$.data[0].concertId").value(1L))
			.andExpect(jsonPath("$.data[0].singerName").value("Singer"))
			.andExpect(jsonPath("$.data[0].concertDate").exists())
			.andExpect(jsonPath("$.data[0].capacity").value(100L))
			.andExpect(jsonPath("$.data[0].location").value("Location"));
	}

	@Test
	@DisplayName("예약 가능한 좌석 조회 성공 테스트")
	void getAvailableSeatsSuccessTest() throws Exception {
		// given
		SeatResponse seatResponse = new SeatResponse();
		seatResponse.setSeatId(1L);
		seatResponse.setSeatNumber(1);
		seatResponse.setReserved(false);
		seatResponse.setConcertResponse(new ConcertResponse(1L, "Concert Name", null));

		when(bookingFacade.getAvailableSeats(any(SeatRequest.class))).thenReturn(Collections.singletonList(seatResponse));

		// when & then
		mockMvc.perform(get("/bookings/available-seats")
				.param("concertOptionId", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].seatId").value(1L))
			.andExpect(jsonPath("$.data[0].seatNumber").value(1))
			.andExpect(jsonPath("$.data[0].reserved").value(false))
			.andExpect(jsonPath("$.data[0].concertResponse.concertId").value(1L))
			.andExpect(jsonPath("$.data[0].concertResponse.concertName").value("Concert Name"));
	}
}