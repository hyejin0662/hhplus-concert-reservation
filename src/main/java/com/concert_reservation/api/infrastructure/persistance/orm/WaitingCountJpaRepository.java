package com.concert_reservation.api.infrastructure.persistance.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountJpaRepository extends JpaRepository<WaitingCount, Long> {



	@Query("SELECT SUM(w.count) FROM WaitingCount w")
	int getCount();


}
