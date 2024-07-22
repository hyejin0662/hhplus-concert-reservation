package com.concert_reservation.api.infrastructure.persistance.orm;

import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointJpaRepository extends JpaRepository<Point, Long> {

  Optional<Point> findPointByUserUserId(String userId);

  void deleteByUserUserId(String userId);

}