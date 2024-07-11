package com.concert_reservation.api.infrastructure.persistance.orm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.common.type.TokenStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {


	Optional<Token> findByUserUserId(String userId);


	// TODO : 파라미터로 넘어온 token의 token 아이디와 sql의 tokenId가 일치한 것을 확인하고자합니다.
	@Modifying
	@Query("UPDATE Token t SET t.tokenStatus = 'PROCESSING' WHERE t.tokenId = :tokenId AND t.tokenStatus = com.concert_reservation.common.type.TokenStatus.WAITING")
	int updateStatusProcessing(Long tokenId);

	@Query("SELECT t FROM Token t WHERE t.tokenStatus = com.concert_reservation.common.type.TokenStatus.WAITING ORDER BY t.waitingAt ASC")
	Optional<Token> findFirstByTokenStatusOrderByWaitingAtAsc();

	List<Token> findAllByTokenStatusAndExpirationAtBefore(TokenStatus tokenStatus, LocalDateTime now);
}