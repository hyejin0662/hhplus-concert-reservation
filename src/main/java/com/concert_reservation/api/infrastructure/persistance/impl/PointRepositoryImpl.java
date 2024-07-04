package com.concert_reservation.api.infrastructure.persistance.impl;


import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

  private final PointJpaRepository pointJpaRepository;

}
