package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Token;

public interface TokenRepository {

	Token save(Token token);

	Optional<Token> findByUserId(String userID);

	void updateStatusProcessing(Token token);

	Optional<Token> findFirstByTokenStatusOrderByWaitingAtAsc();

	Optional<Token> findById(Long tokenId);
	List<Token> findAll();
	void deleteById(Long tokenId);
}
