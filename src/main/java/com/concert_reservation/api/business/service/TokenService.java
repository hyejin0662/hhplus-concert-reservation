package com.concert_reservation.api.business.service;

import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.application.dto.response.TokenValidateResponse;
import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;

public interface TokenService {
	TokenInfo createToken(TokenCommand tokenCommand);
	TokenInfo getToken(String concertCode);
	TokenInfo getUserTokenInfo(String userId);

	void scheduledUpdateTokenStatusToProcessing();

	void completeProcessingTokens(String userId);

	void scheduledExpireProcessingTokens();

	void scheduledExpireWaitingTokens();


	TokenValidateInfo validateToken(String token);
}