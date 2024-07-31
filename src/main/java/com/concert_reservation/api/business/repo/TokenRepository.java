package com.concert_reservation.api.business.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.domain.WaitingToken;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.common.type.TokenStatus;

public interface TokenRepository {

	Token save(Token token);

	WaitingToken enqueue(String userId);

	Optional<Token> findByUserId(String userId);

	Optional<Token> findById(Long tokenId);
	List<Token> findAll();
	void deleteById(Long tokenId);

	List<Token> findAllByTokenStatusAndExpirationAtBefore(TokenStatus tokenStatus, LocalDateTime now);

	List<Token> saveAll(List<Token> tokensToBeExpired);

	int countByTokenStatusAndWaitingAtBefore(TokenStatus tokenStatus, LocalDateTime waitingAt);

	void deleteByUserId(String userId);

	Optional<Token> getFirstWaitingToken();



	Long getTokenRank(String userId);
}
