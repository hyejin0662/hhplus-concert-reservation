package com.concert_reservation.api.infrastructure.persistance.orm;

import com.concert_reservation.api.business.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointJpaRepository extends JpaRepository<Point, Long> {

  Point findPointsByUserId(String userId);
}