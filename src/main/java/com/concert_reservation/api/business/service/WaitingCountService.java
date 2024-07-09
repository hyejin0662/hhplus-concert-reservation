package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountService {
    WaitingCount createWaiting(TokenRequest tokenRequest);
}