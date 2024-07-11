package com.concert_reservation.api.presentation.controller;

import java.util.List;

import com.concert_reservation.api.application.dto.request.AvailableDatesRequest;
import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.AvailableDatesResponse;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.facade.BookingFacade;
import com.concert_reservation.common.model.WebResponseData;

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
    public WebResponseData<BookingResponse> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = bookingFacade.createBooking(bookingRequest);
        return WebResponseData.ok(response);
    }

    @GetMapping("/{userId}")
    public WebResponseData<BookingResponse> getBooking(@PathVariable String userId) {
        BookingResponse response = bookingFacade.getBooking(userId);
        return WebResponseData.ok(response);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingFacade.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }


    // todo -> 예약 가능한 날짜
    // concert -> conert -> concertoption을 찾고, 해당하는 날짜에 예약을 조회해서, 예약이 안된 거 리턴



    @GetMapping("/available-dates")
    public WebResponseData<List<AvailableDatesResponse>> getAvailableDates(@ModelAttribute AvailableDatesRequest seatRequest) {
        List<AvailableDatesResponse> seatResponses = bookingFacade.getAvailableDates(seatRequest);
        return WebResponseData.ok(seatResponses);
    }


    @GetMapping("/available-seats")
    public WebResponseData<List<SeatResponse>> getAvailableSeats(@ModelAttribute SeatRequest seatRequest) {
        List<SeatResponse> seatResponses = bookingFacade.getAvailableSeats(seatRequest);
        return WebResponseData.ok(seatResponses);
    }



}
