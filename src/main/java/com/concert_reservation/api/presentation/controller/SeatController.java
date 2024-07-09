package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.AvailableSeatsRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.application.facade.SeatFacade;
import com.concert_reservation.api.business.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatFacade seatFacade;

    @PostMapping("/available")
    public ResponseEntity<List<SeatResponse>> getAvailableSeats(@RequestBody AvailableSeatsRequest availableSeatsRequest) {
        List<SeatResponse> seatResponses = seatFacade.getAvailableSeats(availableSeatsRequest);
        return ResponseEntity.ok(seatResponses);
    }
}