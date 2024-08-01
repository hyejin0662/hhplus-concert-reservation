package com.concert_reservation.api.business.service;

import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;

public interface TokenService {
	TokenInfo createToken(TokenCommand tokenCommand);
	TokenInfo getWaitingToken(String concertCode);

	void transfer();

	void completeProcessingTokens(String userId);

	void decrementCounter();


	TokenValidateInfo validateToken(String token);


}