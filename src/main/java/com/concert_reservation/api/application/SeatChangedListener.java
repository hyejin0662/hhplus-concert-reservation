package com.concert_reservation.api.application;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.concert_reservation.api.domain.concert.event.SeatChangedEvent;
import com.concert_reservation.api.domain.mock.MockApiClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeatChangedListener {
    private final MockApiClient mockApiClient;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSeatChangedEvent(SeatChangedEvent event) {
        mockApiClient.sendSeatInfo(event.getSeatId(), event.isReserved());
    }
}