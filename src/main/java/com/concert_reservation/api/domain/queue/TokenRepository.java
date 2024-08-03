package com.concert_reservation.api.domain.queue;

import java.util.List;

import com.concert_reservation.api.domain.queue.model.WaitingToken;

public interface TokenRepository {

	WaitingToken issueToken(String userId);

	Long getTokenRank(String userId);

	void increaseCounter();

	void decrease();

	long getActiveTokens();

	void activateTokens(WaitingToken token);

	void removeFromProcessingQueue(String token);

	boolean isActive(String token);

	List<WaitingToken> getTopWaitingTokens(long activeTokenCount);

}
