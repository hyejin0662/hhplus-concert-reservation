package com.concert_reservation.api.infrastructure.persistance.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.entity.Count;

public interface CountJpaRepository extends JpaRepository<Count, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Count c SET c.count = c.count + 1 WHERE c.contId = 1")
	void incrementCount();

	@Modifying
	@Transactional
	@Query("UPDATE Count c SET c.count = c.count - 1 WHERE c.contId = 1")
	void decrementCount();

	@Query("SELECT c.count FROM Count c WHERE c.contId = 1")
	int getCount();
}
