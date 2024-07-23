package com.concert_reservation.api.infrastructure.persistance.orm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.common.type.TokenStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {


	Optional<Token> findByUserUserId(String userId);


	Optional<Token> findFirstByTokenStatusOrderByWaitingAtAsc(TokenStatus tokenStatus);

	List<Token> findAllByTokenStatusAndExpirationAtBefore(TokenStatus tokenStatus, LocalDateTime now);

	@Query("SELECT COUNT(t) FROM Token t WHERE t.tokenStatus = :tokenStatus AND t.waitingAt < :waitingAt")
	int countByTokenStatusAndWaitingAtBefore(TokenStatus tokenStatus, LocalDateTime waitingAt);


	void deleteByUserUserId(String userId);
}