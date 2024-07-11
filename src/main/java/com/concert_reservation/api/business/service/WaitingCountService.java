package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.WaitingCountResponse;
import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountService {
    WaitingCount createWaiting(TokenRequest tokenRequest);

    WaitingCountResponse getWaitingCount(Long countId);
    List<WaitingCountResponse> getAllWaitingCounts();
    void incrementWaitingCount(Long countId);
    void decrementWaitingCount(Long countId);
}