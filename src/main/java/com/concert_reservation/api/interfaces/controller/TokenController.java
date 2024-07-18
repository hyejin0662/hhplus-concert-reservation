package com.concert_reservation.api.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.application.facade.TokenFacade;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenFacade tokenFacade;

    // todo -> post 요청에서는 requestParam을 쓰지 않음, requestParam은 get 요청
    // @RequestBody를 이용해서 dto로 받아야 함

    @PostMapping
    @Operation(summary = "토큰 생성 및 대기열 진입")
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) {
        return tokenFacade.createToken(tokenRequest);
    }


    // 이 api를 통해서 클라이언트가 폴링 요청을 함
    // 즉 주기적으로 이 api를 호출해서 본인의 현재 대기열 상태 (몇번째인지, 몇시간 남았는지)를 알려달라고 함
    // TODO : 클라이언트가 몇번째인지, 몇 분 남았는지
    @GetMapping
    @Operation(summary = "클라이언트 토큰 정보 요청 (폴링)")
    public TokenResponse getToken(@RequestParam String userId) {
        return tokenFacade.getToken(userId);
    }


}