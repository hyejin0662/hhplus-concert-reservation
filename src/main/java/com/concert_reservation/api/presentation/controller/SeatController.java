package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.facade.SeatFacade;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatFacade seatFacade;

    // @PostMapping("/available")
    // public ResponseEntity<List<SeatResponse>> getAvailableSeats(@RequestBody SeatRequest seatRequest) {
    //     List<SeatResponse> seatResponses = seatFacade.getAvailableSeats(seatRequest);
    //     return ResponseEntity.ok(seatResponses);
    // }
}