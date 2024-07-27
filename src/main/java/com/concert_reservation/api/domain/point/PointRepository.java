package com.concert_reservation.api.domain.point;


import java.util.List;
import java.util.Optional;

public interface PointRepository {

  Optional<Point> findPointByUserId(String userId);
  Optional<Point> findById(Long pointId);
  List<Point> findAll();
  Point save(Point point);
  void deleteById(Long pointId);

	Point findByUserId(String bookingId);

	void deleteByUserId(String userId);
}
