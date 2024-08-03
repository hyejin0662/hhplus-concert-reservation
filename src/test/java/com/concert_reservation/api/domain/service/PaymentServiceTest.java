package com.concert_reservation.api.domain.service;

import com.concert_reservation.api.application.point.PaymentCommand;
import com.concert_reservation.api.application.point.PaymentInfo;
import com.concert_reservation.api.domain.point.PaymentService;
import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.api.domain.point.PointRepository;
import com.concert_reservation.common.exception.CustomException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "PaymentService 테스트")
class PaymentServiceTest {

	@Mock
	private PointRepository pointRepository;

	@InjectMocks
	private PaymentService paymentService;

	private Point point;
	private PaymentCommand paymentCommand;

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

		paymentCommand = PaymentCommand.builder()
			.userId("user123")
			.amount(200L)
			.concertOptionId(1L)
			.paymentMethod("Credit Card")
			.build();
	}

	@Test
	@DisplayName("포인트 결제 성공 테스트")
	void payPointSuccessTest() {
		// given
		when(pointRepository.getPoint(anyString())).thenReturn(Optional.of(point));

		// when
		PaymentInfo paymentInfo = paymentService.payPoint(paymentCommand);

		// then
		assertThat(paymentInfo).isNotNull();
		assertThat(paymentInfo.getUserId()).isEqualTo(point.getUser().getUserId());
		assertThat(paymentInfo.getAmount()).isEqualTo(300L);
		verify(pointRepository, times(1)).getPoint(anyString());
		verify(pointRepository, times(1)).save(point);
	}

	@Test
	@DisplayName("포인트 결제 시 사용자 찾을 수 없음 테스트")
	void payPointUserNotFoundTest() {
		// given
		when(pointRepository.getPoint(anyString())).thenReturn(Optional.empty());

		// when
		CustomException exception = assertThrows(CustomException.class, () -> {
			paymentService.payPoint(paymentCommand);
		});

		// then
		assertThat(exception).isNotNull();
		verify(pointRepository, times(1)).getPoint(anyString());
		verify(pointRepository, never()).save(any(Point.class));
	}

	@Test
	@DisplayName("포인트 결제 시 잔액 부족 테스트")
	void payPointInsufficientFundsTest() {
		// given
		paymentCommand.setAmount(600L); // More than available balance
		when(pointRepository.getPoint(anyString())).thenReturn(Optional.of(point));

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			paymentService.payPoint(paymentCommand);
		});

		// then
		assertThat(exception).isNotNull();
		verify(pointRepository, times(1)).getPoint(anyString());
		verify(pointRepository, never()).save(any(Point.class));
	}
}
