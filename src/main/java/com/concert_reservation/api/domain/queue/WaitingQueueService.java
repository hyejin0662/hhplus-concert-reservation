package com.concert_reservation.api.domain.queue;


import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WaitingQueueService {

	private final TokenRepository tokenRepository;

	// 토큰 발급 및 조회 (대기 번호 발급)
	// 토큰을 활성화 처리 (n 입장 받기)
	// 토큰을 만료 처리 (n 명 입자 불가처리)

	public TokenInfo issueToken(TokenCommand.IssueToken command) {
		Token token = Token.issueToken(command);
		Token savedToken = tokenRepository.save(token); // save한 것은 곧 대기열 진입
		return TokenInfo.from(token);
	}

	public void activateTokens() {
		// 현재 이용중인 고객이 몇명인지 확인
		// 최대인원 - 이용가능한 인원
		// 대기열에 있는 사람중에 가장 앞에 있는 사람들 들여보내기(processing)
		var activeTokens = tokenRepository.getActiveTokens();
		var availableTokenCount = WaitingQueue.getAvailableTokenCount(activeTokens.size());
		var waitingTokens = tokenRepository.getWaitngTokensByCount(availableTokenCount);
		waitingTokens.forEach(toekn -> token.active());
	}

	public void expireTokens() {
		// 5분 지난 토큰을 들고온다.
		// 만료시킨다.
		List<Token> activeTokens = tokenRepository.findAllActiveTokensOver5Min();
		activeTokens.forEach(token -> token.expire());
	}
}
