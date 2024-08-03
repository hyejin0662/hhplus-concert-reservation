// package com.concert_reservation.api.business.repo;
//
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;
//
// import com.concert_reservation.api.business.model.domain.ActiveToken;
// import com.concert_reservation.api.business.model.domain.WaitingToken;
// import com.concert_reservation.api.business.model.entity.Token;
// import com.concert_reservation.common.type.TokenStatus;
//
// public interface TokenRepositoryRdb {
// 	WaitingToken enqueue(String userId);
//
// 	long getActiveTokenCount();
//
// 	Long getTokenRank(String userId);
//
//
//
//
//
//
// 	void incrementCounter();
//
// 	void decrementCounter();
//
//
// 	boolean isActive(String token);
//
//
// 	Token save(Token token);
//
//
// 	Optional<Token> findByUserId(String userId);
// 	Optional<Token> findById(Long tokenId);
// 	List<Token> findAll();
//
// 	void deleteById(Long tokenId);
//
// 	List<Token> findAllByTokenStatusAndExpirationAtBefore(TokenStatus tokenStatus, LocalDateTime now);
//
// 	List<Token> saveAll(List<Token> tokensToBeExpired);
//
// 	int countByTokenStatusAndWaitingAtBefore(TokenStatus tokenStatus, LocalDateTime waitingAt);
//
// 	void deleteByUserId(String userId);
//
// 	Optional<Token> getFirstWaitingToken();
//
//
//
//
//
//
// 	Optional<String> getFirstTokenFromQueue();
//
// 	void transfer(WaitingToken token);
//
// 	void removeFromProcessingQueue(String token);
//
// 	// 대기열에서 토큰 제거 메서드
// 	void removeFromQueue(String token);
//
// 	// Redis에 토큰 상태 저장 메서드 추가
// 	void saveTokenToRedis(Token token);
//
// 	// 처리열에 토큰이 존재하는지 확인하는 메서드 추가
//
//
// 	List<WaitingToken> getTopWaitingTokens(long activeTokenCount);
//
// 	List<ActiveToken> getTokens();
// }
