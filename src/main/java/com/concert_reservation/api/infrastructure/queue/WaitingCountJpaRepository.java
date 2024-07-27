package com.concert_reservation.api.infrastructure.queue;

import com.concert_reservation.api.domain.queue.WaitingCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaitingCountJpaRepository extends JpaRepository<WaitingCount, Long> {



	@Query("SELECT SUM(w.count) FROM WaitingCount w")
	int getCount();


}
