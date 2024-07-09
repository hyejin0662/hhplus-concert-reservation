package com.concert_reservation.api.application.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.AvailableSeatsRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.service.impl.SeatServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeatFacade {
    private final SeatServiceImpl seatServiceImpl;

    public List<SeatResponse> getAvailableSeats(AvailableSeatsRequest availableSeatsRequest) {
        return seatServiceImpl.getAvailableSeats(availableSeatsRequest)
                              .stream()
                              .map(SeatResponse::from)
                              .collect(Collectors.toList());
    }
}