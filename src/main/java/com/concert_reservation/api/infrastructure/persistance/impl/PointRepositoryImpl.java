package com.concert_reservation.api.infrastructure.persistance.impl;


import java.util.List;
import java.util.Optional;

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


  @Override
  public Optional<Point> findById(Long pointId) {
    return pointJpaRepository.findById(pointId);
  }

  @Override
  public List<Point> findAll() {
    return pointJpaRepository.findAll();
  }

  @Override
  public Point save(Point point) {
    return pointJpaRepository.save(point);
  }

  @Override
  public void deleteById(Long pointId) {
    pointJpaRepository.deleteById(pointId);
  }
}
