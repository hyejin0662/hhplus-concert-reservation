package com.concert_reservation.api.business.repo;


import com.concert_reservation.api.business.model.entity.Point;

public interface PointRepository {

  Point findPointsByUserId(String userId);
}
