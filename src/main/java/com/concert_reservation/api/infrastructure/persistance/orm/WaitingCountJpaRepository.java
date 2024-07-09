package com.concert_reservation.api.infrastructure.persistance.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountJpaRepository extends JpaRepository<WaitingCount, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE WaitingCount c SET c.count = c.count + 1 WHERE c.contId = 1")
	void incrementCount();

	@Modifying
	@Transactional
	@Query("UPDATE WaitingCount c SET c.count = c.count - 1 WHERE c.contId = 1")
	void decrementCount();

	// @Query("SELECT c.count FROM WaitingCount c WHERE c.contId = 1")
	// int getCount();

	@Query("SELECT SUM(w.count) FROM WaitingCount w")
	int getCount();

	@Query("SELECT COUNT(w) FROM WaitingCount w WHERE w.concert.concertId = :concertId")
	Long countByConcertId(Long concertId);

}
