package com.concert_reservation.api.infrastructure.persistance.impl;

import org.springframework.stereotype.Repository;

import com.concert_reservation.api.business.repo.CountRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.CountJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CountRepositoryImpl implements CountRepository {
	private final CountJpaRepository countJpaRepository;


	@Override
	public int countPlus() {
		countJpaRepository.incrementCount();
		return countJpaRepository.getCount();
	}

	@Override
	public int countMinus() {
		countJpaRepository.decrementCount();
		return countJpaRepository.getCount();
	}

	@Override
	public int getCount() {
		return countJpaRepository.getCount();
	}

}
