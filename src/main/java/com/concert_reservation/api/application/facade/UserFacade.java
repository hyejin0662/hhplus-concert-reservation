package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.TokenResponse;
import com.concert_reservation.api.business.service.impl.TokenServiceImpl;
import com.concert_reservation.api.business.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserServiceImpl userServiceImpl;
    private final TokenServiceImpl tokenServiceImpl;

    public Long getUserBalance(String userId) {
        return userServiceImpl.getUserBalance(userId);
    }

    public Long chargeUserBalance(String userId, Long amount) {
        return userServiceImpl.chargeUserBalance(userId, amount);
    }

}