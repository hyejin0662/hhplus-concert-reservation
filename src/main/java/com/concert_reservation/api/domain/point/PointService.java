package com.concert_reservation.api.domain.point;


import com.concert_reservation.api.application.point.PointCommand;
import com.concert_reservation.api.application.point.PointInfo;

import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {

  private final PointRepository pointRepository;

  @Transactional
  public PointInfo charge(PointCommand pointCommand) {
    Point point = pointRepository.getPoint(pointCommand.getUserId()).orElseThrow();
    point.charge(pointCommand.getAmount());
    return PointInfo.from(point);
  }


  public PointInfo getPoint(Long pointId) {
    Point point = pointRepository.getPoint(pointId).orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));
    return PointInfo.from(point);
  }

  @Transactional
  public PointInfo use(PointCommand pointCommand) {
    Point point = pointRepository.getPoint(pointCommand.getUserId()).orElseThrow();
    point.use(pointCommand.getAmount());
    return PointInfo.from(point);
  }

}