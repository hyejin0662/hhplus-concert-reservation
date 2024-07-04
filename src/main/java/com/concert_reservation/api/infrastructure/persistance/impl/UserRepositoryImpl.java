package com.concert_reservation.api.infrastructure.persistance.impl;

import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

}
