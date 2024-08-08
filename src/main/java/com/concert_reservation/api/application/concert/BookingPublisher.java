package com.concert_reservation.api.application.concert;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.domain.common.dto.event.BookingCompletedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishBookingCompletedEvent(String userId, Long concertOptionId) {
        BookingCompletedEvent event = new BookingCompletedEvent(userId, concertOptionId);
        eventPublisher.publishEvent(event);
    }
}