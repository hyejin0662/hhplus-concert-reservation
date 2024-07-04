package com.concert_reservation.api.infrastructure.persistance.impl;

import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ConcertJpaRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.TokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

  private final TokenJpaRepository tokenJpaRepository;

}
