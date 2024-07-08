package com.concert_reservation.api.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.application.facade.TokenFacade;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenFacade tokenFacade;

    // todo -> post 요청에서는 requestParam을 쓰지 않음, requestParam은 get 요청
    // @RequestBody를 이용해서 dto로 받아야 함

    @PostMapping
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) {
        return tokenFacade.createToken(tokenRequest);
    }


    // 이 api를 통해서 클라이언트가 폴링 요청을 함
    // 즉 주기적으로 이 api를 호출해서 본인의 현재 대기열 상태 (몇번째인지, 몇시간 남았는지)를 알려달라고 함
    // TODO : 클라이언트가 몇번째인지, 몇 분 남았는지
    @GetMapping
    public TokenResponse getToken(@RequestParam String concertCode) {
        return tokenFacade.getToken(concertCode);
    }

}