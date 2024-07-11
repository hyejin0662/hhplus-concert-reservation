package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.application.dto.response.WaitingCountResponse;
import com.concert_reservation.api.business.model.dto.command.WaitingCountCommand;
import com.concert_reservation.api.business.model.dto.info.WaitingCountInfo;
import com.concert_reservation.api.business.model.entity.WaitingCount;

public interface WaitingCountService {
    WaitingCountInfo createWaiting(TokenRequest tokenRequest);

    WaitingCountInfo getWaitingCount(Long countId);
    List<WaitingCountInfo> getAllWaitingCounts();
    void incrementWaitingCount(Long countId);
    void decrementWaitingCount(Long countId);
}