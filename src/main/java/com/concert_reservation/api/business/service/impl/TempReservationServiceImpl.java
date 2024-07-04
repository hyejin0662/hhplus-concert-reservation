package com.concert_reservation.api.business.service.impl;


import com.concert_reservation.api.business.model.entity.TempReservation;
import com.concert_reservation.api.business.repo.TempReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempReservationServiceImpl {

  private final TempReservationRepository tempReservationRepository;

}
