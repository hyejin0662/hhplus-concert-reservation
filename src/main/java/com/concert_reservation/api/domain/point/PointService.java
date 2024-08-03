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


  public PointInfo charge(PointCommand pointCommand) {
    Point point = pointRepository
        .findPointByUserId(pointCommand.getUserId())
        .orElseThrow();
    point.addAmount(pointCommand.getAmount());
    return PointInfo.from(point);
  }


  public PointInfo getPoint(Long pointId) {
    Point point = pointRepository.findById(pointId)
        .orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));
    return PointInfo.from(point);
  }


  public PointInfo use(PointCommand pointCommand) {
    Point point = pointRepository
        .findPointByUserId(pointCommand.getUserId())
        .orElseThrow();
    point.use(pointCommand.getAmount());
    return PointInfo.from(point);
  }

}