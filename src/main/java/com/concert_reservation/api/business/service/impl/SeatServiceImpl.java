package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.business.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl {
  private final SeatRepository seatRepository;

}
