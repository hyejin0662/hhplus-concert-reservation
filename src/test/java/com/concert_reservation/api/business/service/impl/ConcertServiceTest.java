package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.business.model.dto.command.ConcertCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.repo.ConcertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConcertService 테스트")
class ConcertServiceTest {


	@Mock
	private ConcertRepository concertRepository;

	@InjectMocks
	private ConcertServiceImpl concertServiceImpl;

	private Concert concert;
	private ConcertCommand concertCommand;

	@BeforeEach
	void setUp() {
		concert = new Concert();
		concert.setConcertId(1L);
		concert.setConcertName("콘서트 이름");

		concertCommand = new ConcertCommand();
		concertCommand.setConcertName("새로운 콘서트");
	}

	@Test
	@DisplayName("콘서트 조회 테스트")
	void getConcertTest() {
		// given
		when(concertRepository.findById(anyLong())).thenReturn(Optional.of(concert));

		// when
		ConcertInfo concertInfo = concertServiceImpl.getConcert(1L);

		// then
		assertThat(concertInfo).isNotNull();
		assertThat(concertInfo.getConcertName()).isEqualTo(concert.getConcertName());
		verify(concertRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("콘서트 조회 실패 테스트")
	void getConcertNotFoundTest() {
		// given
		when(concertRepository.findById(anyLong())).thenReturn(Optional.empty());

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			concertServiceImpl.getConcert(1L);
		});

		// then
		assertThat(exception.getMessage()).isEqualTo("Concert not found");
		verify(concertRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("콘서트 업데이트 테스트")
	void updateConcertTest() {
		// given
		when(concertRepository.findById(anyLong())).thenReturn(Optional.of(concert));
		when(concertRepository.save(any(Concert.class))).thenReturn(concert);

		// when
		ConcertInfo updatedConcertInfo = concertServiceImpl.updateConcert(1L, concertCommand);

		// then
		assertThat(updatedConcertInfo).isNotNull();
		assertThat(updatedConcertInfo.getConcertName()).isEqualTo(concertCommand.getConcertName());
		verify(concertRepository, times(1)).findById(anyLong());
		verify(concertRepository, times(1)).save(any(Concert.class));
	}

	@Test
	@DisplayName("콘서트 업데이트 실패 테스트")
	void updateConcertNotFoundTest() {
		// given
		when(concertRepository.findById(anyLong())).thenReturn(Optional.empty());

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			concertServiceImpl.updateConcert(1L, concertCommand);
		});

		// then
		assertThat(exception.getMessage()).isEqualTo("Concert not found");
		verify(concertRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("콘서트 삭제 테스트")
	void deleteConcertTest() {
		// given
		doNothing().when(concertRepository).deleteById(anyLong());

		// when
		concertServiceImpl.deleteConcert(1L);

		// then
		verify(concertRepository, times(1)).deleteById(anyLong());
	}

	@Test
	@DisplayName("콘서트 정보 조회 테스트")
	void getConcertInfoTest() {
		// given
		when(concertRepository.findById(anyLong())).thenReturn(Optional.of(concert));

		// when
		ConcertInfo concertInfo = concertServiceImpl.getConcert(1L);

		// then
		assertThat(concertInfo).isNotNull();
		assertThat(concertInfo.getConcertName()).isEqualTo(concert.getConcertName());
		verify(concertRepository, times(1)).findById(anyLong());
	}
}
