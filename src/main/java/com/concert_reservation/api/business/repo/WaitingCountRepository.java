package com.concert_reservation.api.business.repo;

import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountRepository {
	int countPlus();
	int countMinus();
	int getCount();
	Long countByConcertId(Long concertId);

	WaitingCount saveWaitingCount(WaitingCount waitingCount);
}
