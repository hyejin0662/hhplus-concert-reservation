package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.business.service.impl.BookingServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingFacade {
    private final BookingServiceImpl bookingServiceImpl;

    public BookingResponse bookSeats(BookingRequest bookingRequest) {
        return BookingResponse.from(bookingServiceImpl.bookSeats(bookingRequest));
    }
}