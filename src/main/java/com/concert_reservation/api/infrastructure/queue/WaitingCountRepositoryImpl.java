package com.concert_reservation.api.infrastructure.queue;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.concert_reservation.api.domain.queue.WaitingCount;
import com.concert_reservation.api.domain.queue.WaitingCountRepository;
import com.concert_reservation.api.infrastructure.queue.WaitingCountJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitingCountRepositoryImpl implements WaitingCountRepository {
	private final WaitingCountJpaRepository waitingCountJpaRepository;



	@Override
	public int getCount() {
		return waitingCountJpaRepository.getCount();
	}



	@Override
	public WaitingCount save(WaitingCount waitingCount) {
		return waitingCountJpaRepository.save(waitingCount);
	}

	@Override
	public Optional<WaitingCount> findById(Long countId) {
		return waitingCountJpaRepository.findById(countId);
	}

	@Override
	public List<WaitingCount> findAll() {
		return waitingCountJpaRepository.findAll();
	}

}
