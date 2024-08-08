package com.concert_reservation.api.application;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.domain.common.dto.event.BookingCompletedEvent;
import com.concert_reservation.api.domain.mock.MockApiClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingCompletedListener {
    private final MockApiClient mockApiClient;

    @Async
    @EventListener
    public void handleBookingCompletedEvent(BookingCompletedEvent event) {
        mockApiClient.sendBookingInfo(event.getUserId(), event.getConcertOptionId());
    }
}