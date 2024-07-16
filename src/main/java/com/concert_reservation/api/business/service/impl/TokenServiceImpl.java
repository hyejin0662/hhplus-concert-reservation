package com.concert_reservation.api.business.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.model.entity.WaitingCount;
import com.concert_reservation.api.business.repo.WaitingCountRepository;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.business.service.TokenService;
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

		// todo -> 토큰을 유저의 정보를 이용해서 만드는 것은 나중에 고도화
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
		TokenInfo tokenInfo = DtoConverter.convert(token, TokenInfo.class);
		tokenInfo.setWaitingNumber(waitingNumber);
		return tokenInfo;
	}

	/**
	 *     내부적으로 스케줄링 하여 돌아가는 로직이므로 외부에서 파라미터를 받을 필요가 없음
	 *     대기열에 있는 항목들 중에서 주기적으로 조회해서
	 *     가장 최신인 것부터 (선착순) 하나씩 상태를 변경해주는 로직
	 *     지금 없는 거 = 조회 하는 로직 = 무엇을 처리할지 대상이 없음 = 대상 은 어디서 구함 ? 대상은 대기열에 앞전에 넣어놓은 거를 꺼내다가 선착순 별로 필터링해서
	 *     가장 최신것을 변경해주어야 함
	 *     언제? 처리열 혹은 서버가 감당하기로 한 수치보다 현재 감당하고 있는 수치가 적은 경우
	 *     만약에 꽉 찼다? 그러면 그냥 내비두고 다음 스케줄링에서 판단
	 */

  @Override
  @Transactional
  public void scheduledUpdateTokenStatusToProcessing() {

	  Optional<Token> tokenOptional =tokenRepository.findFirstByTokenStatusOrderByWaitingAtAsc();

	  if (tokenOptional.isEmpty()){
		  return;
	  }

    int count = waitingCountRepository.getCount();

    if (count <= 50) {

       tokenRepository.updateStatusProcessing(tokenOptional.get());
    }

    }

	@Override
	public void completeProcessingTokens(String userId) {
		Token token = tokenRepository.findByUserId(userId).orElseThrow();
		token.doExpire();
		WaitingCount waitingCount = waitingCountRepository.findById(1L).orElseThrow();
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

}