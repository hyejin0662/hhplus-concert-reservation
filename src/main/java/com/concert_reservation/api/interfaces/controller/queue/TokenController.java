package com.concert_reservation.api.interfaces.controller.queue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.interfaces.controller.queue.dto.TokenRequest;
import com.concert_reservation.api.interfaces.controller.queue.dto.TokenResponse;
import com.concert_reservation.api.application.queue.TokenFacade;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenFacade tokenFacade;


    /**
     * 토큰을 생성하고 대기열에 진입시킵니다.
     *
     * <p>이 메서드는 클라이언트의 요청을 받아 새로운 토큰을 생성하고,
     * 해당 토큰을 대기열에 추가합니다.</p>
     *
     * @param tokenRequest 토큰 생성을 위한 요청 데이터
     * @return 생성된 토큰과 관련된 응답 데이터
     */
    @PostMapping
    @Operation(summary = "토큰 생성 및 대기열 진입")
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) {
        return tokenFacade.createToken(tokenRequest);
    }



    /**
     * 클라이언트의 현재 대기열 상태를 조회합니다.
     *
     * <p>이 메서드는 클라이언트가 주기적으로 호출하여 자신의 현재 대기열 상태,
     * 예를 들어 대기열에서 몇 번째에 위치해 있는지, 예상 대기 시간이 얼마나 남았는지를 조회합니다.</p>
     *
     * @param userId 토큰 정보를 조회할 사용자 ID
     * @return 사용자의 현재 대기열 상태와 관련된 응답 데이터
     */
    @GetMapping
    @Operation(summary = "클라이언트 토큰 정보 요청 (폴링)")
    public TokenResponse getToken(@RequestParam String userId) {
        return tokenFacade.getToken(userId);
    }


}