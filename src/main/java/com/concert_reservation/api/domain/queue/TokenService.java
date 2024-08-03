package com.concert_reservation.api.domain.queue;

import static com.concert_reservation.common.token.QueueTokenGenerator.*;
import static com.concert_reservation.common.type.QueuePolicy.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.application.queue.TokenCommand;
import com.concert_reservation.api.application.queue.TokenInfo;
import com.concert_reservation.api.application.queue.TokenValidateInfo;
import com.concert_reservation.api.domain.queue.model.WaitingToken;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;


	@Transactional
	public TokenInfo enqueue(TokenCommand tokenCommand) {
		WaitingToken enqueue = tokenRepository.enqueue(tokenCommand.getUserId());
		return TokenInfo.from(enqueue);

	}

	/**
	 * 주어진 사용자 ID를 기반으로 해당 사용자의 토큰 정보를 조회합니다.
	 * 사용자의 대기 상태와 대기 번호를 파악하여 처리열에서의 위치를 반환합니다.
	 *
	 * @param userId 토큰 정보를 조회할 사용자 ID
	 * @return 주어진 사용자 ID에 대한 토큰 정보, 대기열에서의 순위 포함
	 */

	public TokenInfo getWaitingToken(String userId) {
		String tokenValue = generateToken(userId);
		Long rank = tokenRepository.getTokenRank(tokenValue);
		return TokenInfo.builder().userId(userId).tokenValue(tokenValue).rank(rank).build();
	}

	/**
	 * 대기열에서 처리열로 토큰을 옮기는 작업을 수행합니다.
	 * 이 메서드는 Redis를 사용하여 만료된 이벤트를 처리하고,
	 * 대기열과 처리열에서 토큰 수를 관리합니다.
	 *
	 * <p>처리 과정은 다음과 같습니다:</p>
	 * <ul>
	 *     <li>현재 처리열에 있는 토큰 수를 확인합니다.</li>
	 *     <li>처리열에 더 추가할 수 있는 인원이 있는지 검사합니다.</li>
	 *     <li>대기열에서 추가할 수 있는 토큰을 가져옵니다.</li>
	 *     <li>가져온 토큰을 처리열로 옮기고, 카운터를 증가시킵니다.</li>
	 * </ul>
	 *
	 * <p>만료된 토큰은 Redis 이벤트 리스너를 통해 처리됩니다.</p>
	 *
	 * @see com.concert_reservation.api.interfaces.eventhandler.RedisKeyExpirationListener
	 */

	@Transactional
	public void transfer() {

		long activeTokenCount = tokenRepository.getActiveTokenCount();

		if (activeTokenCount > ACTIVE_USER_LIMIT.getValue()) {
			return;
		}

		List<WaitingToken> tokens = tokenRepository.getTopWaitingTokens(activeTokenCount);

		for (WaitingToken token : tokens) {
			tokenRepository.transfer(token);
			tokenRepository.incrementCounter();
		}
	}

	/**
	 * 주어진 토큰 값을 검증합니다.
	 *
	 * <p>레디스 처리열에서 해당 토큰이 존재하는지 확인합니다.
	 * 토큰이 존재하면 사용 가능한 유저로 간주하고,
	 * 그렇지 않으면 예외를 발생시킵니다.</p>
	 *
	 * @param tokenValue 검증할 토큰 값
	 * @return 토큰이 유효한 경우, {@code TokenValidateInfo} 객체를 반환
	 * @throws CustomException 토큰이 존재하지 않을 경우 발생
	 */

	public TokenValidateInfo validateToken(String tokenValue) {
		if (tokenRepository.isActive(tokenValue)) {
			return TokenValidateInfo.of(tokenValue, true);
		}


		throw new CustomException(GlobalResponseCode.TOKEN_NOT_FOUND);
	}

	/**
	 * 주어진 사용자 ID를 기반으로 처리 중인 토큰을 완료 처리합니다.
	 *
	 * <p>이 메서드는 결제 시에 호출되며, 해당 사용자의 토큰을 만료 처리합니다.</p>
	 *
	 * <p>처리 과정은 다음과 같습니다:</p>
	 * <ul>
	 *     <li>사용자 ID를 이용해 해시 함수로 토큰 값을 찾습니다.</li>
	 *     <li>해당 토큰 값을 처리열에서 제거합니다.</li>
	 *     <li>카운터를 감소시킵니다.</li>
	 * </ul>
	 *
	 * @param userId 토큰을 완료 처리할 사용자 ID
	 * @throws CustomException 사용자 ID에 해당하는 토큰이 없을 경우 발생
	 */

	@Transactional
	public void completeProcessingTokens(String userId) {
		tokenRepository.removeFromProcessingQueue(String.valueOf(generateToken(userId)));
		tokenRepository.decrease();
	}


	@Transactional
	public void decrementCounter() {
		tokenRepository.decrease();
	}

}