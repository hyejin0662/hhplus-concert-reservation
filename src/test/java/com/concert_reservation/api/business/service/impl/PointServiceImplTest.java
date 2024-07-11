package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.repo.PointRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PointServiceImpl 테스트")
class PointServiceImplTest {

	@Mock
	private PointRepository pointRepository;

	@InjectMocks
	private PointServiceImpl pointServiceImpl;

	private Point point;
	private PointCommand pointCommand;

	@BeforeEach
	void setUp() {
		point = Point.builder()
			.pointId(1L)
			.userId("user123")
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
	@DisplayName("포인트 충전 성공 테스트")
	void chargePointSuccessTest() {
		// given
		when(pointRepository.findPointByUserIdOptional(anyString())).thenReturn(Optional.of(point));

		// when
		PointInfo pointInfo = pointServiceImpl.chargePoint(pointCommand);

		// then
		assertThat(pointInfo).isNotNull();
		assertThat(pointInfo.getUserId()).isEqualTo(point.getUserId());
		assertThat(pointInfo.getAmount()).isEqualTo(700L);
		verify(pointRepository, times(1)).findPointByUserIdOptional(anyString());
		verify(pointRepository, times(1)).save(point);
	}

	@Test
	@DisplayName("포인트 조회 성공 테스트")
	void getPointSuccessTest() {
		// given
		when(pointRepository.findById(anyLong())).thenReturn(Optional.of(point));

		// when
		PointInfo pointInfo = pointServiceImpl.getPoint(1L);

		// then
		assertThat(pointInfo).isNotNull();
		assertThat(pointInfo.getUserId()).isEqualTo(point.getUserId());
		verify(pointRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("포인트 조회 실패 테스트")
	void getPointNotFoundTest() {
		// given
		when(pointRepository.findById(anyLong())).thenReturn(Optional.empty());

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			pointServiceImpl.getPoint(1L);
		});

		// then
		assertThat(exception.getMessage()).isEqualTo("Point not found");
		verify(pointRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("포인트 차감 성공 테스트")
	void deductPointSuccessTest() {
		// given
		when(pointRepository.findPointByUserIdOptional(anyString())).thenReturn(Optional.of(point));

		// when
		PointInfo pointInfo = pointServiceImpl.deductPoint(pointCommand);

		// then
		assertThat(pointInfo).isNotNull();
		assertThat(pointInfo.getUserId()).isEqualTo(point.getUserId());
		assertThat(pointInfo.getAmount()).isEqualTo(300L);
		verify(pointRepository, times(1)).findPointByUserIdOptional(anyString());
		verify(pointRepository, times(1)).save(point);
	}

	@Test
	@DisplayName("포인트 차감 시 사용자 찾을 수 없음 테스트")
	void deductPointNotFoundTest() {
		// given
		when(pointRepository.findPointByUserIdOptional(anyString())).thenReturn(Optional.empty());

		// when
		NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
			pointServiceImpl.deductPoint(pointCommand);
		});

		// then
		assertThat(exception).isNotNull();
		verify(pointRepository, times(1)).findPointByUserIdOptional(anyString());
		verify(pointRepository, never()).save(any(Point.class));
	}

	@Test
	@DisplayName("포인트 차감 시 잔액 부족 테스트")
	void deductPointInsufficientFundsTest() {
		// given
		pointCommand.setAmount(600L); // More than available balance
		when(pointRepository.findPointByUserIdOptional(anyString())).thenReturn(Optional.of(point));

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			pointServiceImpl.deductPoint(pointCommand);
		});

		// then
		assertThat(exception).isNotNull();
		verify(pointRepository, times(1)).findPointByUserIdOptional(anyString());
		verify(pointRepository, never()).save(any(Point.class));
	}
}
