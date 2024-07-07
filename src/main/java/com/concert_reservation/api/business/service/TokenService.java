package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.entity.Token;

public interface TokenService {
	TokenInfo createToken(TokenCommand tokenCommand);
	TokenInfo getToken(String concertCode);

	void updateTokenStatusToProcessing();
}