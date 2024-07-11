package com.concert_reservation.api.business.service;

import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;

public interface TokenService {
	TokenInfo createToken(TokenCommand tokenCommand);
	TokenInfo getToken(String concertCode);

	void scheduledUpdateTokenStatusToProcessing();

	void completeProcessingTokens(String userId);

	@Transactional
	void scheduledExpireProcessingTokens();

	void scheduledExpireWaitingTokens();

}