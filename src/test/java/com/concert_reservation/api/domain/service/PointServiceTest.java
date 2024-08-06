package com.concert_reservation.api.domain.service;

import com.concert_reservation.api.application.point.PointCommand;
import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.api.domain.point.PointService;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.api.domain.point.PointRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PointService 테스트")
class PointServiceTest {

	@Mock
	private PointRepository pointRepository;

	@InjectMocks
	private PointService pointService;

	private Point point;
	private PointCommand pointCommand;

	@BeforeEach
	void setUp() {
		User user = User.builder()
			.userId("user123")
			.build();

		point = Point.builder()
			.pointId(1L)
			.user(user)
			.amount(500L)
			.paymentTime(LocalDateTime.now())
			.paymentMethod("Credit Card")
			.build();

		pointCommand = PointCommand.builder()
			.userId("user123")
			.amount(200L)
			.build();
	}


	@Test
	@DisplayName("포인트 차감 시 사용자 찾을 수 없음 테스트")
	void deductPointNotFoundTest() {
		// given
		when(pointRepository.getPoint(anyString())).thenReturn(Optional.empty());

		// when
		NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
			pointService.use(pointCommand);
		});

		// then
		assertThat(exception).isNotNull();
		verify(pointRepository, times(1)).getPoint(anyString());
		verify(pointRepository, never()).save(any(Point.class));
	}

	@Test
	@DisplayName("포인트 차감 시 잔액 부족 테스트")
	void deductPointInsufficientFundsTest() {
		// given
		pointCommand.setAmount(600L); // More than available balance
		when(pointRepository.getPoint(anyString())).thenReturn(Optional.of(point));

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			pointService.use(pointCommand);
		});

		// then
		assertThat(exception).isNotNull();
		verify(pointRepository, times(1)).getPoint(anyString());
		verify(pointRepository, never()).save(any(Point.class));
	}
}
