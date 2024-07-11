package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.facade.ConcertFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final ConcertFacade concertFacade;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = concertFacade.createBooking(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long bookingId) {
        BookingResponse response = concertFacade.getBooking(bookingId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = concertFacade.updateBooking(bookingId, bookingRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        concertFacade.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
