package com.concert_reservation.api.infrastructure.point;


import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.api.domain.point.PointRepository;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

  private final PointJpaRepository pointJpaRepository;

  @Override
  public Optional<Point> getPoint(String userId) {
    return pointJpaRepository.findPointByUserUserId(userId);
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

  @Override
  public Point findByUserId(String userId) {
    return pointJpaRepository.findPointByUserUserId(userId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.BOOKING_NOT_FOUND_BY_POINT));
  }

  @Override
  public void deleteByUserId(String userId) {
    pointJpaRepository.deleteByUserUserId(userId);
  }

}
