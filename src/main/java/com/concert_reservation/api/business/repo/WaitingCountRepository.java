package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountRepository {



	int getCount();


	WaitingCount save(WaitingCount waitingCount);

	Optional<WaitingCount> findById(Long countId);

	List<WaitingCount> findAll();

}
