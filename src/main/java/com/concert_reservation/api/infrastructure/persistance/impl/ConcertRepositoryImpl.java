package com.concert_reservation.api.infrastructure.persistance.impl;

import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ConcertJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

  private final ConcertJpaRepository concertJpaRepository;

}
