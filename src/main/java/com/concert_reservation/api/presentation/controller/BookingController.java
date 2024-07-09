package com.concert_reservation.api.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.facade.BookingFacade;
import com.concert_reservation.api.business.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingFacade bookingFacade;

    @PostMapping
    public ResponseEntity<BookingResponse> bookSeats(@RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingFacade.bookSeats(bookingRequest);
        return ResponseEntity.ok(bookingResponse);
    }
}