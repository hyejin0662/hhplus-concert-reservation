package com.concert_reservation.api.domain.point;


import com.concert_reservation.api.application.point.PointCommand;
import com.concert_reservation.api.application.point.PointInfo;

import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

  private final PointRepository pointRepository;


  public PointInfo chargePoint(PointCommand pointCommand) {
    Point point = pointRepository.getPoint(pointCommand.getUserId())
        .map(existingPoint -> {
          existingPoint.charge(pointCommand.getAmount());
          return existingPoint;
        })
        .orElseGet(pointCommand::toEntity);

    pointRepository.save(point);
    return PointInfo.from(point);
  }


  public PointInfo getPoint(Long pointId) {
    Point point = pointRepository.findById(pointId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));
    return PointInfo.from(point);
  }


  public PointInfo pay(PointCommand pointCommand) {
    Point point = pointRepository.getPoint(pointCommand.getUserId())
        .orElseThrow();

    point.pay(pointCommand.getAmount());
    pointRepository.save(point);
    return PointInfo.from(point);
  }

}