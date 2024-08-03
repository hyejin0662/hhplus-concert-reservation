package com.concert_reservation.api.business.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.domain.ActiveToken;
import com.concert_reservation.api.business.model.domain.WaitingToken;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.common.type.TokenStatus;

public interface TokenRepository {

	WaitingToken enqueue(String userId);

	Long getTokenRank(String userId);

	void incrementCounter();

	void decrementCounter();

	long getActiveTokenCount();

	void transfer(WaitingToken token);

	void removeFromProcessingQueue(String token);

	boolean isActive(String token);

	List<WaitingToken> getTopWaitingTokens(long activeTokenCount);

}
