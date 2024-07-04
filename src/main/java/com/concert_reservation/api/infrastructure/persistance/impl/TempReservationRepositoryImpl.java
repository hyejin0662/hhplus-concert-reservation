package com.concert_reservation.api.infrastructure.persistance.impl;


import com.concert_reservation.api.business.repo.TempReservationRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.TempReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TempReservationRepositoryImpl implements TempReservationRepository {

  private final TempReservationJpaRepository tempReservationJpaRepository;

}
