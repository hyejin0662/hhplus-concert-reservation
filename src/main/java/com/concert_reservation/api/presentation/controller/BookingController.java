package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.facade.BookingFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = bookingFacade.createBooking(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long bookingId) {
        BookingResponse response = bookingFacade.getBooking(bookingId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = bookingFacade.updateBooking(bookingId, bookingRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingFacade.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
