package com.concert_reservation.api.domain.service;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.concert_reservation.api.application.queue.TokenCommand;
import com.concert_reservation.api.domain.queue.TokenService;
import com.concert_reservation.api.application.queue.TokenValidateInfo;
import com.concert_reservation.api.domain.queue.model.Token;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.api.domain.queue.TokenRepository;
import com.concert_reservation.api.domain.user.UserRepository;
import com.concert_reservation.api.domain.queue.WaitingCountRepository;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.concert_reservation.common.type.TokenStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@DisplayName("TokenService 클래스 테스트")
class TokenServiceTest {

	@Mock
	private TokenRepository tokenRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private WaitingCountRepository waitingCountRepository;

	@InjectMocks
	private TokenService tokenService;


	private Token token;
	private User user;
	private TokenCommand tokenCommand;

	@BeforeEach
	@DisplayName("각 테스트 전에 공통 데이터 초기화")
	void setUp() {
		user = User.builder()
			.userId("user123")
			.name("김삿갓")
			.build();

		token = Token.builder()
			.tokenId(1L)
			.user(user)
			.waitingAt(LocalDateTime.now())
			.expirationAt(LocalDateTime.now().plusMinutes(5))
			.tokenStatus(TokenStatus.WAITING)
			.build();

		tokenCommand = TokenCommand.builder()
			.userId("user123")
			.build();
	}


	@Test
	@DisplayName("validateToken: 토큰이 활성 상태일 때 TokenValidateInfo를 반환하는 테스트")
	void validateTokenActiveTest() {
		// given
		when(tokenRepository.isActive(anyString())).thenReturn(true);

		// when
		TokenValidateInfo result = tokenService.validateToken("validToken");

		// then
		assertThat(result.isValid()).isTrue();
		verify(tokenRepository, times(1)).isActive(anyString());
	}

	@Test
	@DisplayName("validateToken: 토큰이 비활성 상태일 때 CustomException을 발생시키는 테스트")
	void validateTokenInactiveTest() {
		// given
		when(tokenRepository.isActive(anyString())).thenReturn(false);

		// when
		CustomException exception = assertThrows(CustomException.class, () -> {
			tokenService.validateToken("invalidToken");
		});

		// then
		assertThat(exception.getGlobalResponseCode()).isEqualTo(GlobalResponseCode.TOKEN_NOT_FOUND);
		verify(tokenRepository, times(1)).isActive(anyString());
	}

}
