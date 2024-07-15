package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.infrastructure.persistance.impl.BookingRepositoryImpl;
import com.concert_reservation.api.infrastructure.persistance.orm.BookingJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookingRepositoryImpl 테스트")
class BookingRepositoryImplTest {

	@Mock
	private BookingJpaRepository bookingJpaRepository;

	@InjectMocks
	private BookingRepositoryImpl bookingRepositoryImpl;

	private Booking booking;

	@BeforeEach
	void setUp() {
		booking = Booking.builder()
			.bookingId(1L)
			.bookingTime(LocalDateTime.now())
			.build();
	}

	@Test
	@DisplayName("예약 저장 테스트")
	void saveBookingTest() {
		// given
		when(bookingJpaRepository.save(any(Booking.class))).thenReturn(booking);

		// when
		Booking savedBooking = bookingRepositoryImpl.save(booking);

		// then
		assertThat(savedBooking).isNotNull();
		assertThat(savedBooking.getBookingId()).isEqualTo(booking.getBookingId());
		verify(bookingJpaRepository, times(1)).save(booking);
	}

	@Test
	@DisplayName("여러 예약 저장 테스트")
	void saveAllBookingsTest() {
		// given
		List<Booking> bookings = Arrays.asList(booking);
		when(bookingJpaRepository.saveAll(anyList())).thenReturn(bookings);

		// when
		List<Booking> savedBookings = bookingRepositoryImpl.saveAll(bookings);

		// then
		assertThat(savedBookings).isNotNull();
		assertThat(savedBookings.size()).isEqualTo(1);
		verify(bookingJpaRepository, times(1)).saveAll(bookings);
	}

	@Test
	@DisplayName("예약 ID로 조회 테스트")
	void findByIdTest() {
		// given
		when(bookingJpaRepository.findById(anyLong())).thenReturn(Optional.of(booking));

		// when
		Optional<Booking> foundBooking = bookingRepositoryImpl.findById(1L);

		// then
		assertThat(foundBooking).isPresent();
		assertThat(foundBooking.get().getBookingId()).isEqualTo(booking.getBookingId());
		verify(bookingJpaRepository, times(1)).findById(1L);
	}

	@Test
	@DisplayName("모든 예약 조회 테스트")
	void findAllBookingsTest() {
		// given
		List<Booking> bookings = Arrays.asList(booking);
		when(bookingJpaRepository.findAll()).thenReturn(bookings);

		// when
		List<Booking> allBookings = bookingRepositoryImpl.findAll();

		// then
		assertThat(allBookings).isNotNull();
		assertThat(allBookings.size()).isEqualTo(1);
		verify(bookingJpaRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("예약 ID로 삭제 테스트")
	void deleteByIdTest() {
		// given
		doNothing().when(bookingJpaRepository).deleteById(anyLong());

		// when
		bookingRepositoryImpl.deleteById(1L);

		// then
		verify(bookingJpaRepository, times(1)).deleteById(1L);
	}

	@Test
	@DisplayName("사용자 ID로 예약 조회 테스트")
	void findByUserIdTest() {
		// given
		when(bookingJpaRepository.findByUserUserId(anyString())).thenReturn(Optional.of(booking));

		// when
		Optional<Booking> foundBooking = bookingRepositoryImpl.findByUserId("user123");

		// then
		assertThat(foundBooking).isPresent();
		assertThat(foundBooking.get().getBookingId()).isEqualTo(booking.getBookingId());
		verify(bookingJpaRepository, times(1)).findByUserUserId("user123");
	}
}
