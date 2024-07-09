package com.concert_reservation.api.infrastructure.persistance.impl;


import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

  private final PointJpaRepository pointJpaRepository;

  @Override
  public Point findPointsByUserId(String userId) {
    return pointJpaRepository.findPointsByUserId(userId);
  }
}
