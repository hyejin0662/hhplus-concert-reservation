package com.concert_reservation.api.business.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.model.entity.WaitingCount;
import com.concert_reservation.api.business.repo.WaitingCountRepository;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.GlobalResponseCode;
import com.concert_reservation.common.type.TokenStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;
	private final WaitingCountRepository waitingCountRepository;

	@Override
	@Transactional
	public TokenInfo createToken(TokenCommand tokenCommand) {
		User user = userRepository.findById(tokenCommand.getUserId())
			.orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));

		String tokenCode = UUID.randomUUID().toString();
		var expirationTime = LocalDateTime.now().plusMinutes(5);

		Token token = Token.builder()
			.user(user)
			.tokenId(Long.valueOf(tokenCode.replace("-", "").substring(0, 18)))
			.waitingAt(LocalDateTime.now())
			.expirationAt(expirationTime)
			.tokenStatus(TokenStatus.WAITING)
			.build();

		Token savedToken = tokenRepository.save(token); // save한 것은 곧 대기열 진입

		waitingCountRepository.findById(1L).orElse(WaitingCount.createDefault()).incrementCount(); // 카운트 증가

		return DtoConverter.convert(savedToken, TokenInfo.class);
	}



	@Override
	public TokenInfo getToken(String userId) {
		Token token = tokenRepository.findByUserId(userId)
			.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
		return DtoConverter.convert(token, TokenInfo.class);
	}
	// UserID 를 받았을 때 그 사람의 토큰정보를 파악하고 대기상태/대기번호 등을 조회
	@Override
	public TokenInfo getUserTokenInfo(String userId) {
		Token token = tokenRepository.findByUserId(userId)
			.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
		int waitingNumber = tokenRepository.countByTokenStatusAndWaitingAtBefore(TokenStatus.WAITING, token.getWaitingAt()) + 1;
		return token.toTokenInfoWithWaitingNumber(waitingNumber);
	}


	@Override
	@Transactional
	public void scheduledUpdateTokenStatusToProcessing() {
		tokenRepository.findFirstByTokenStatusOrderByWaitingAtAsc()
			.ifPresent(token -> {
				int count = waitingCountRepository.getCount();
				if (count <= 50) {
					token.updateStatusToProcessing();
					tokenRepository.save(token);
				}
			});
	}

	@Override
	public void completeProcessingTokens(String userId) {
		Token token = tokenRepository.findByUserId(userId)
			.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
		token.doExpire();
		WaitingCount waitingCount = waitingCountRepository.findById(1L)
			.orElseThrow(() -> new CustomException(GlobalResponseCode.WAITING_COUNT_NOT_FOUND));
		waitingCount.decrementCount();
	}



	@Override
	@Transactional
	public void scheduledExpireProcessingTokens(){
		List<Token> tokensToBeExpired = tokenRepository.findAllByTokenStatusAndExpirationAtBefore(TokenStatus.PROCESSING, LocalDateTime.now());
		tokensToBeExpired.forEach(Token::doExpire);
		tokenRepository.saveAll(tokensToBeExpired);
	}

	@Override
	@Transactional
	public void scheduledExpireWaitingTokens() {
		List<Token> tokensToBeExpired = tokenRepository.findAllByTokenStatusAndExpirationAtBefore(TokenStatus.WAITING, LocalDateTime.now());
		tokensToBeExpired.forEach(Token::doExpire);
		tokenRepository.saveAll(tokensToBeExpired);
	}

	@Override
	public TokenValidateInfo validateToken(String tokenCode) {
		// 1. 토큰이 존재하는지 확인
		// 2. 만료 여부 확인
		// 3. 상태가 'PROCESSING'인지 확인
		Token token = tokenRepository.findById(Long.valueOf(tokenCode.replace("-", "").substring(0, 18)))
			.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
		if (token.isExpired()) {
			throw new CustomException(GlobalResponseCode.TOKEN_EXPIRED);
		}
		if (!token.isProcessing()) {
			throw new CustomException(GlobalResponseCode.TOKEN_NOT_PROCESSING);
		}
		return token.toTokenValidateInfo();
	}

}