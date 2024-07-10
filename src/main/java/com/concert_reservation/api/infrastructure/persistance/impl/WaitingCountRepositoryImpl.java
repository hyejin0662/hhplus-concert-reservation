package com.concert_reservation.api.infrastructure.persistance.impl;

import org.springframework.stereotype.Repository;

import com.concert_reservation.api.business.model.entity.WaitingCount;
import com.concert_reservation.api.business.repo.WaitingCountRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.WaitingCountJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitingCountRepositoryImpl implements WaitingCountRepository {
	private final WaitingCountJpaRepository waitingCountJpaRepository;


	@Override
	public int countPlus() {
		waitingCountJpaRepository.incrementCount();
		return waitingCountJpaRepository.getCount();
	}

	@Override
	public int countMinus() {
		waitingCountJpaRepository.decrementCount();
		return waitingCountJpaRepository.getCount();
	}

	@Override
	public int getCount() {
		return waitingCountJpaRepository.getCount();
	}

	@Override
	public Long countByConcertId(Long concertId) {
		return waitingCountJpaRepository.countByConcertId(concertId);
	}

	@Override
	public WaitingCount saveWaitingCount(WaitingCount waitingCount) {
		return null;
	}

}
