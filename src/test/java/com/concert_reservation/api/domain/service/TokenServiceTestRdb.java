// package com.concert_reservation.api.business.service;
//
// import static org.assertj.core.api.Assertions.*;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.any;
// import static org.mockito.Mockito.anyLong;
// import static org.mockito.Mockito.*;
//
// import java.time.LocalDateTime;
// import java.util.Optional;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
//
// import com.concert_reservation.api.business.model.dto.command.TokenCommand;
// import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;
// import com.concert_reservation.api.business.model.entity.Token;
// import com.concert_reservation.api.business.model.entity.User;
// import com.concert_reservation.api.business.model.entity.WaitingCount;
// import com.concert_reservation.api.business.repo.TokenRepository;
// import com.concert_reservation.api.business.user.UserRepository;
// import com.concert_reservation.api.business.repo.WaitingCountRepository;
// import com.concert_reservation.common.exception.CustomException;
// import com.concert_reservation.common.type.GlobalResponseCode;
// import com.concert_reservation.common.type.TokenStatus;
//
// @ExtendWith(MockitoExtension.class)
// @DisplayName("TokenService 클래스 테스트")
// class TokenServiceTestRdb {
//
// 	@Mock
// 	private TokenRepository tokenRepository;
//
// 	@Mock
// 	private UserRepository userRepository;
//
// 	@Mock
// 	private WaitingCountRepository waitingCountRepository;
//
// 	@InjectMocks
// 	private TokenServiceImpl tokenService;
//
//
// 	private Token token;
// 	private User user;
// 	private TokenCommand tokenCommand;
//
// 	@BeforeEach
// 	@DisplayName("각 테스트 전에 공통 데이터 초기화")
// 	void setUp() {
// 		user = User.builder()
// 			.userId("user123")
// 			.name("김삿갓")
// 			.build();
//
// 		token = Token.builder()
// 			.tokenId(1L)
// 			.user(user)
// 			.waitingAt(LocalDateTime.now())
// 			.expirationAt(LocalDateTime.now().plusMinutes(5))
// 			.tokenStatus(TokenStatus.WAITING)
// 			.build();
//
// 		tokenCommand = TokenCommand.builder()
// 			.userId("user123")
// 			.build();
// 	}
//
// 	@Test
// 	@DisplayName("createToken: 유저를 찾지 못했을 때 CustomException을 발생시키는 테스트")
// 	@Disabled("RDB -> Redis로 교체에 따라 disable 처리")
// 	void createTokenUserNotFoundTest() {
// 		// given
// 		when(userRepository.findById(anyString())).thenReturn(Optional.empty());
//
// 		// when
// 		CustomException exception = assertThrows(CustomException.class, () -> {
// 			tokenService.createToken(tokenCommand);
// 		});
//
// 		// then
// 		assertThat(exception.getGlobalResponseCode()).isEqualTo(GlobalResponseCode.USER_NOT_FOUND);
// 		verify(userRepository, times(1)).findById(anyString());
// 		verify(tokenRepository, never()).save(any(Token.class));
// 		verify(waitingCountRepository, never()).findById(anyLong());
// 	}
//
// 	@Test
// 	@DisplayName("getToken: 토큰을 찾지 못했을 때 CustomException을 발생시키는 테스트")
// 	@Disabled("RDB -> Redis로 교체에 따라 disable 처리")
// 	void getTokenNotFoundTest() {
// 		// given
// 		when(tokenRepository.findByUserId(anyString())).thenReturn(Optional.empty());
//
// 		// when
// 		CustomException exception = assertThrows(CustomException.class, () -> {
// 			tokenService.getWaitingToken("user123");
// 		});
//
// 		// then
// 		assertThat(exception.getGlobalResponseCode()).isEqualTo(GlobalResponseCode.TOKEN_NOT_FOUND);
// 		verify(tokenRepository, times(1)).findByUserId(anyString());
// 	}
//
// 	@Test
// 	@DisplayName("scheduledUpdateTokenStatusToProcessing: 대기중인 토큰 상태를 처리중으로 업데이트하는 테스트")
// 	@Disabled("RDB -> Redis로 교체에 따라 disable 처리")
// 	void scheduledUpdateTokenStatusToProcessingTest() {
// 		// given
// 		when(tokenRepository.getFirstWaitingToken()).thenReturn(Optional.of(token));
// 		when(waitingCountRepository.getCount()).thenReturn(40);
//
// 		// when
// 		tokenService.transfer();
//
// 		// then
// 		verify(tokenRepository, times(1)).getFirstWaitingToken();
// 	}
//
// 	@Test
// 	@DisplayName("completeProcessingTokens: 토큰 상태를 완료로 변경하는 테스트")
// 	@Disabled("RDB -> Redis로 교체에 따라 disable 처리")
// 	void completeProcessingTokensTest() {
// 		// given
// 		when(tokenRepository.findByUserId(anyString())).thenReturn(Optional.of(token));
// 		when(waitingCountRepository.findById(anyLong())).thenReturn(Optional.of(WaitingCount.createDefault()));
//
// 		// when
// 		tokenService.completeProcessingTokens("user123");
//
// 		// then
// 		verify(tokenRepository, times(1)).findByUserId(anyString());
// 		verify(waitingCountRepository, times(1)).findById(anyLong());
// 		assertThat(token.getTokenStatus()).isEqualTo(TokenStatus.EXPIRED);
// 	}
//
// 	@Test
// 	@DisplayName("validateToken: 토큰이 활성 상태일 때 TokenValidateInfo를 반환하는 테스트")
// 	void validateTokenActiveTest() {
// 		// given
// 		when(tokenRepository.isActive(anyString())).thenReturn(true);
//
// 		// when
// 		TokenValidateInfo result = tokenService.validateToken("validToken");
//
// 		// then
// 		assertThat(result.isValid()).isTrue();
// 		verify(tokenRepository, times(1)).isActive(anyString());
// 	}
//
// 	@Test
// 	@DisplayName("validateToken: 토큰이 비활성 상태일 때 CustomException을 발생시키는 테스트")
// 	void validateTokenInactiveTest() {
// 		// given
// 		when(tokenRepository.isActive(anyString())).thenReturn(false);
//
// 		// when
// 		CustomException exception = assertThrows(CustomException.class, () -> {
// 			tokenService.validateToken("invalidToken");
// 		});
//
// 		// then
// 		assertThat(exception.getGlobalResponseCode()).isEqualTo(GlobalResponseCode.TOKEN_NOT_FOUND);
// 		verify(tokenRepository, times(1)).isActive(anyString());
// 	}
//
// 	// @Test
// 	// @DisplayName("scheduledExpireProcessingTokens: 처리중인 토큰의 만료 처리를 테스트")
// 	// void scheduledExpireProcessingTokensTest() {
// 	// 	// given
// 	// 	List<Token> tokensToBeExpired = List.of(token);
// 	// 	when(tokenRepository.findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.PROCESSING), any(LocalDateTime.class))).thenReturn(tokensToBeExpired);
// 	//
// 	// 	// when
// 	// 	tokenService.scheduledExpireProcessingTokens();
// 	//
// 	// 	// then
// 	// 	verify(tokenRepository, times(1)).findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.PROCESSING), any(LocalDateTime.class));
// 	// 	verify(tokenRepository, times(1)).saveAll(tokensToBeExpired);
// 	// 	assertThat(tokensToBeExpired.get(0).getTokenStatus()).isEqualTo(TokenStatus.EXPIRED);
// 	// }
// 	//
// 	// @Test
// 	// @DisplayName("scheduledExpireWaitingTokens: 대기중인 토큰의 만료 처리를 테스트")
// 	// void scheduledExpireWaitingTokensTest() {
// 	// 	// given
// 	// 	List<Token> tokensToBeExpired = List.of(token);
// 	// 	when(tokenRepository.findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.WAITING), any(LocalDateTime.class))).thenReturn(tokensToBeExpired);
// 	//
// 	// 	// when
// 	// 	tokenService.scheduledExpireWaitingTokens();
// 	//
// 	// 	// then
// 	// 	verify(tokenRepository, times(1)).findAllByTokenStatusAndExpirationAtBefore(eq(TokenStatus.WAITING), any(LocalDateTime.class));
// 	// 	verify(tokenRepository, times(1)).saveAll(tokensToBeExpired);
// 	// 	assertThat(tokensToBeExpired.get(0).getTokenStatus()).isEqualTo(TokenStatus.EXPIRED);
// 	// }
// }
