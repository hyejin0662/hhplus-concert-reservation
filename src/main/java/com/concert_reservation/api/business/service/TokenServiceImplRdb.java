// package com.concert_reservation.api.business.service;
//
// import static com.concert_reservation.common.type.QueuePolicy.*;
//
// import java.time.LocalDateTime;
// import java.util.List;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.concert_reservation.api.business.model.domain.WaitingToken;
// import com.concert_reservation.api.business.model.dto.command.TokenCommand;
// import com.concert_reservation.api.business.model.dto.info.TokenInfo;
// import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;
// import com.concert_reservation.api.business.model.entity.Token;
// import com.concert_reservation.api.business.repo.TokenRepository;
// import com.concert_reservation.api.business.repo.UserRepository;
// import com.concert_reservation.api.business.repo.WaitingCountRepository;
// import com.concert_reservation.common.exception.CustomException;
// import com.concert_reservation.common.type.GlobalResponseCode;
// import com.concert_reservation.common.type.TokenStatus;
//
// import lombok.RequiredArgsConstructor;
//
// @Service
// @RequiredArgsConstructor
// public class TokenServiceImplRdb implements TokenService {
//
// 	private final TokenRepository tokenRepository;
// 	private final UserRepository userRepository;
// 	private final WaitingCountRepository waitingCountRepository;
//
// 	@Override
// 	@Transactional
// 	public TokenInfo createToken(TokenCommand tokenCommand) {
// 		// rdb 부분
// 		// User user = userRepository.findById(tokenCommand.getUserId())
// 		// 	.orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));
//
// 		// String tokenCode = UUID.randomUUID().toString();
// 		// var expirationTime = LocalDateTime.now().plusMinutes(5);
// 		//
// 		// Token token = Token.builder()
// 		// 	.user(user)
// 		// 	.tokenId(Long.valueOf(tokenCode.replace("-", "").substring(0, 18)))
// 		// 	.waitingAt(LocalDateTime.now())
// 		// 	.expirationAt(expirationTime)
// 		// 	.tokenStatus(TokenStatus.WAITING)
// 		// 	.build();
// 		//
// 		// Token savedToken = tokenRepository.save(token); // save한 것은 곧 대기열 진입
// 		//
// 		// waitingCountRepository.findById(1L).orElse(WaitingCount.createDefault()).incrementCount(); // 카운트 증가
// 		//
// 		// return DtoConverter.convert(savedToken, TokenInfo.class);
//
//
//
// 		// redis 부분
// 		WaitingToken enqueue = tokenRepository.enqueue(tokenCommand.getUserId());
// 		return TokenInfo.from(enqueue);
//
// 	}
//
// 	// UserID 를 받았을 때 그 사람의 토큰정보를 파악하고 대기상태/대기번호 등을 조회 -> redis 처리열 n번째 위치
// 	@Override
// 	public TokenInfo getWaitingToken(String userId) {
// 	// 	// rdb 부분
// 	// 	Token token = tokenRepository.findByUserId(userId)
// 	// 		.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
// 	// 	int waitingNumber = tokenRepository.countByTokenStatusAndWaitingAtBefore(TokenStatus.WAITING, token.getWaitingAt()) + 1;
// 	// 	return token.toTokenInfoWithWaitingNumber(waitingNumber);
// 	//
// 	//redis 부분
// 	Long rank = tokenRepository.getTokenRank(userId);
// 		return TokenInfo.builder().userId(userId).rank(rank).build();
// 	}
//
// 	/**
// 	 * 대기열에서 처리열로 이동 놀이공원 방식으로.
// 	 */
// 	@Override
// 	@Transactional
// 	public void transfer() {
// 		// rdb 부분
// 		// tokenRepository.getFirstWaitingToken()
// 		// 	.filter(token -> waitingCountRepository.getCount() <= 50)
// 		// 	.ifPresent(token -> tokenRepository.save(token.markAsProcessing()));
//
// 		// redis 부분
// 		// 서버가 이용할 수 있는 인원수를 100명이라고 했을 때 -> yml 파일에다가 넣어놓는다 -> 못하면 하드코딩
// 		// 처리열에서 토큰이 존재하는 것들 수를 센다 -> 현재 서버를 이용중인 사람 수
// 		// 더 들어올 수 있는 사람 계산: 최대 인원 설정 값 - 처리열 size ->
// 		// 문제가 있다... -> 처리열의 토큰 개수를 셀 수 없다. 왜? 처리열을 key value 형식으로 저장하기 때문에
// 		// 어떻게 해결해야 할까? -> 별도의 카운터 key value 를 두고, 대기열에서 옮길 때마다 +1 , 나가는 애들 -1 (나간다는 건? 결제 완료 한 케이스 or 만료된 케이스) -> 결제 완료했다는 건? 결제 api 가 정상적으로 끝나면 그때 카운ㅌ 호출해서 -1 -> 결제 이벤트를 발행하면 컨슘해서 -1, 만료 이벤트 발행해서 컨슘하기
// 		// 어렵네,...?
// 		// 이 방법을 사용하지 말고 다른 대안은 없나 ?
// 		// 레디스에서 key value로 넣지 말고 set에 다가 넣자.... 존재하는 사람 수 세는 거 한번에 구할 수 있다.
// 		// 문제 없나 ? 만료가 안되네.....
// 		// 만료 시켜주려면 어떻게 해야할까? 스케줄러로 주기적으로 처리열의 있는 토큰을 가져와서 정렬을 해 -> 정렬을 했는데 각각을 봐 -> 시간이 있다 -> 그 시간을 판단 -> 만료가 되었는지 안되었는지 -> expireAt 가 현재시점이랑 비교해서 지났는지 -> delete 요청
// 		//
// 		// 지금까지 한 거는 몇명 옮겨줄지 계산한 거..... -> n명 옮겨야 돼
// 		// tokenRepository에서 최신 토큰(가장 먼저 들어온 애들)을 가져와서
// 		// 옮겨준다 -> 어떻게 ?
// 		// 대기열에 있는 애들 조회 n개 최근에 있는 거 n 개 조회 - sortedSet이니까 가능하다 -> 가져왔다 -> key: token, value : xxx -> 반복문을 돌면서 처리열에 넣어주면 되겠네
// 		// + 대기열 지워주기
//
// 		// 현재 대기열에서 처리열로 토큰을 옮길 수 있는지 확인
// 		long activeTokenCount = tokenRepository.getActiveTokenCount();
//
// 		if (activeTokenCount > ACTIVE_USER_LIMIT.getValue()){
// 			return;
// 		}
//
// 		// 작다 = 더 들어갈 수 있다
// 		List<WaitingToken> tokens = tokenRepository.getTopWaitingTokens(activeTokenCount);
//
// 		for (WaitingToken token : tokens) {
// 			tokenRepository.transfer(token);  // 처리열로 이동
// 			tokenRepository.incrementCounter();
// 		}
// 	}
//
//
// 	@Override
// 	public TokenValidateInfo validateToken(String tokenValue) {
// 		// rdb 부분
// 		// // 1. 토큰이 존재하는지 확인
// 		// // 2. 만료 여부 확인
// 		// // 3. 상태가 'PROCESSING'인지 확인
// 		// Token token = tokenRepository.findById(Long.valueOf(tokenCode.replace("-", "").substring(0, 18)))
// 		// 	.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
// 		// if (token.isExpired()) {
// 		// 	throw new CustomException(GlobalResponseCode.TOKEN_EXPIRED);
// 		// }
// 		// if (!token.isProcessing()) {
// 		// 	throw new CustomException(GlobalResponseCode.TOKEN_NOT_PROCESSING);
// 		// }
// 		// return token.toTokenValidateInfo();
//
//
// 		//redis로 구현
// 		// 레디스 처리열에 있기만 그건 있는 거네 = 사용가능한 유저인거네
// 		// 있으면 주고
// 		// 없으면 = 넌 없어 = 예외
//
// 		if (tokenRepository.isActive(tokenValue)){
// 			return TokenValidateInfo.of(tokenValue, true);
// 		};
//
// 		throw new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND);
// 	}
//
//
//
//
//
//
// // todo -> 현재 결제시에 만료처리하는 로직임(완료)
// 	// 레디스인 경우에도 해야 하므로 주석처리하고 레디스 경우도 구현
// 	@Override
// 	@Transactional
// 	public void completeProcessingTokens(String userId) {
// 		// rdb 구분
// 		// Token token = tokenRepository.findByUserId(userId)
// 		// 	.orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
// 		// token.doExpire();
// 		// WaitingCount waitingCount = waitingCountRepository.findById(1L)
// 		// 	.orElseThrow(() -> new CustomException(GlobalResponseCode.WAITING_COUNT_NOT_FOUND));
// 		// waitingCount.decrementCount();
//
//
// 		// todo -> userId로 해시함수 돌리면 token value 나온다 (완료)
// 		// 해당 토큰 value 지워주면 된다
// 		// + 카운터도 -1 해주어야 한다
// 		Token token = tokenRepository.findByUserId(userId).orElseThrow(() -> new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND));
// 		tokenRepository.removeFromProcessingQueue(String.valueOf(token.getTokenId()));
// 		tokenRepository.decrementCounter();
// 	}
// 	@Override
// 	@Transactional
// 	public void decrementCounter() {
// 		tokenRepository.decrementCounter();
// 	}
//
//
//
// 	@Override
// 	@Transactional
// 	public void scheduledExpireProcessingTokens(){
// 		List<Token> tokensToBeExpired = tokenRepository.findAllByTokenStatusAndExpirationAtBefore(TokenStatus.PROCESSING, LocalDateTime.now());
// 		tokensToBeExpired.forEach(Token::doExpire);
// 		tokenRepository.saveAll(tokensToBeExpired);
// 	}
//
// 	@Override
// 	@Transactional
// 	public void scheduledExpireWaitingTokens() {
// 		List<Token> tokensToBeExpired = tokenRepository.findAllByTokenStatusAndExpirationAtBefore(TokenStatus.WAITING, LocalDateTime.now());
// 		tokensToBeExpired.forEach(Token::doExpire);
// 		tokenRepository.saveAll(tokensToBeExpired);
// 	}
//
//
// }