package com.concert_reservation.api.infrastructure.persistance.impl;


import com.concert_reservation.api.business.repo.ReservationRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository{

  private final ReservationJpaRepository reservationJpaRepository;

}
