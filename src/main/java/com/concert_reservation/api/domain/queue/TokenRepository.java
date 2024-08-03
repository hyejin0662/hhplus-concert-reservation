package com.concert_reservation.api.domain.queue;

import java.util.List;

import com.concert_reservation.api.domain.queue.model.WaitingToken;

public interface TokenRepository {

	WaitingToken enqueue(String userId);

	Long getTokenRank(String userId);

	void incrementCounter();

	void decrease();

	long getActiveTokenCount();

	void transfer(WaitingToken token);

	void removeFromProcessingQueue(String token);

	boolean isActive(String token);

	List<WaitingToken> getTopWaitingTokens(long activeTokenCount);

}
