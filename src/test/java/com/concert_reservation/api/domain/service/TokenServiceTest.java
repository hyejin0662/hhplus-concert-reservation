package com.concert_reservation.api.domain.service;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.concert_reservation.api.domain.queue.TokenCommand;
import com.concert_reservation.api.domain.queue.Token;
import com.concert_reservation.api.domain.queue.WaitingQueueService;
import com.concert_reservation.api.domain.user.User;
import com.concert_reservation.api.domain.queue.WaitingCount;
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
	private WaitingQueueService tokenService;

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
	@DisplayName("createToken: 유저를 찾지 못했을 때 CustomException을 발생시키는 테스트")
	void createTokenUserNotFoundTest() {
		// given
		when(userRepository.findById(anyString())).thenReturn(Optional.empty());

		// when
		CustomException exception = assertThrows(CustomException.class, () -> {
			tokenService.createToken(tokenCommand);
		});

		// then
		assertThat(exception.getGlobalResponseCode()).isEqualTo(GlobalResponseCode.USER_NOT_FOUND);
		verify(userRepository, times(1)).findById(anyString());
		verify(tokenRepository, never()).save(any(Token.class));
		verify(waitingCountRepository, never()).findById(anyLong());
	}

	@Test
	@DisplayName("getToken: 토큰을 찾지 못했을 때 CustomException을 발생시키는 테스트")
	void getTokenNotFoundTest() {
		// given
		when(tokenRepository.findByUserId(anyString())).thenReturn(Optional.empty());

		// when
		CustomException exception = assertThrows(CustomException.class, () -> {
			tokenService.getToken("user123");
		});

		// then
		assertThat(exception.getGlobalResponseCode()).isEqualTo(GlobalResponseCode.TOKEN_NOT_FOUND);
		verify(tokenRepository, times(1)).findByUserId(anyString());
	}

	@Test
	@DisplayName("scheduledUpdateTokenStatusToProcessing: 대기중인 토큰 상태를 처리중으로 업데이트하는 테스트")
	void scheduledUpdateTokenStatusToProcessingTest() {
		// given
		when(tokenRepository.getFirstWaitingToken()).thenReturn(Optional.of(token));
		when(waitingCountRepository.getCount()).thenReturn(40);

		// when
		tokenService.scheduledUpdateTokenStatusToProcessing();

		// then
		verify(tokenRepository, times(1)).getFirstWaitingToken();
	}

	@Test
	@DisplayName("completeProcessingTokens: 토큰 상태를 완료로 변경하는 테스트")
	void completeProcessingTokensTest() {
		// given
		when(tokenRepository.findByUserId(anyString())).thenReturn(Optional.of(token));
		when(waitingCountRepository.findById(anyLong())).thenReturn(Optional.of(WaitingCount.createDefault()));

		// when
		tokenService.completeProcessingTokens("user123");

		// then
		verify(tokenRepository, times(1)).findByUserId(anyString());
		verify(waitingCountRepository, times(1)).findById(anyLong());
		assertThat(token.getTokenStatus()).isEqualTo(TokenStatus.EXPIRED);
	}

	@Test
	@DisplayName("scheduledExpireProcessingTokens: 처리중인 토큰의 만료 처리를 테스트")
	void scheduledExpireProcessingTokensTest() {
		// given
		List<Token> tokensToBeExpired = List.of(token);
		when(tokenRepository.findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.ACTIVE), any(LocalDateTime.class))).thenReturn(tokensToBeExpired);

		// when
		tokenService.scheduledExpireProcessingTokens();

		// then
		verify(tokenRepository, times(1)).findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.ACTIVE), any(LocalDateTime.class));
		verify(tokenRepository, times(1)).saveAll(tokensToBeExpired);
		assertThat(tokensToBeExpired.get(0).getTokenStatus()).isEqualTo(TokenStatus.EXPIRED);
	}

	@Test
	@DisplayName("scheduledExpireWaitingTokens: 대기중인 토큰의 만료 처리를 테스트")
	void scheduledExpireWaitingTokensTest() {
		// given
		List<Token> tokensToBeExpired = List.of(token);
		when(tokenRepository.findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.WAITING), any(LocalDateTime.class))).thenReturn(tokensToBeExpired);

		// when
		tokenService.scheduledExpireWaitingTokens();

		// then
		verify(tokenRepository, times(1)).findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.WAITING), any(LocalDateTime.class));
		verify(tokenRepository, times(1)).saveAll(tokensToBeExpired);
		assertThat(tokensToBeExpired.get(0).getTokenStatus()).isEqualTo(TokenStatus.EXPIRED);
	}
}
