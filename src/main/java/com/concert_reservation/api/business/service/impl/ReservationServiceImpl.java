package com.concert_reservation.api.business.service.impl;


import com.concert_reservation.api.business.repo.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl {
  private final ReservationRepository reservationRepository;

}
