package com.concert_reservation.api.infrastructure.persistance.impl;

import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

  private final SeatJpaRepository seatJpaRepository;
}
