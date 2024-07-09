package com.concert_reservation.api.business.repo;


import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Point;

public interface PointRepository {

  Point findPointsByUserId(String userId);
  Optional<Point> findById(Long pointId);
  List<Point> findAll();
  Point save(Point point);
  void deleteById(Long pointId);
}
