package com.concert_reservation.api.application.facade;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.business.model.dto.command.TokenCommand;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.service.TokenService;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;

    public TokenResponse createToken(TokenRequest tokenRequest) {
        TokenCommand tokenCommand = DtoConverter.convert(tokenRequest, TokenCommand.class);
        TokenInfo tokenInfo = tokenService.createToken(tokenCommand);
        return DtoConverter.convert(tokenInfo, TokenResponse.class);
    }

    public TokenResponse getToken(String concertCode) {
        TokenInfo tokenInfo = tokenService.getToken(concertCode);
        return DtoConverter.convert(tokenInfo, TokenResponse.class);
    }


    // count 조회하고 업데이트를 하는 것이 한 트랜젝션에 묶여야함
    @Scheduled(fixedRate = 2000)// 2초마다
    public void updateTokenStatusToProcessing() {
        tokenService.updateTokenStatusToProcessing();
    }
}