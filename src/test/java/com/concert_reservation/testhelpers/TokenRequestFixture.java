package com.concert_reservation.testhelpers;

import com.concert_reservation.api.interfaces.controller.queue.dto.TokenRequest;
import java.time.LocalDateTime;

public class TokenRequestFixture {

    public static TokenRequest createTokenRequest() {
        TokenRequest request = new TokenRequest();
        request.setUserId("testUser");
        request.setWaitingAt(LocalDateTime.now());
        request.setExpirationAt(LocalDateTime.now().plusMinutes(10));
        request.setTokenStatus("WAITING");
        request.setConcertId(1L);
        return request;
    }
}
