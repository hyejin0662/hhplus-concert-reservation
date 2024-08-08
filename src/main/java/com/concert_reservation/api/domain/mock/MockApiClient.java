package com.concert_reservation.api.domain.mock;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MockApiClient {

    public void sendBookingInfo(String userId, Long concertOptionId) {
        // Mock API 호출 로직 구현
        log.info("예약 정보를 데이터 플랫폼에 보낸다: userId=" + userId + ", concertOptionId=" + concertOptionId);
    }
}