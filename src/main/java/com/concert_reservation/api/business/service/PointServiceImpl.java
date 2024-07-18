package com.concert_reservation.api.business.service;


import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.service.PointService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

  private final PointRepository pointRepository;

  @Override
  public PointInfo chargePoint(PointCommand pointCommand) {

    Point point = pointRepository.findPointByUserIdOptional(pointCommand.getUserId())
        .orElse(pointCommand.toEntity());

    point.addAmount(pointCommand.getAmount());
    pointRepository.save(point);
    return PointInfo.from(point);
  }

  @Override
  public PointInfo getPoint(Long pointId) {
    Point point = pointRepository.findById(pointId)
        .orElseThrow(() -> new RuntimeException("Point not found"));
    return PointInfo.from(point);
  }

  @Override
  public PointInfo deductPoint(PointCommand pointCommand) {
    Point point = pointRepository.findPointByUserIdOptional(pointCommand.getUserId())
        .orElseThrow();

    point.subtractAmount(pointCommand.getAmount());
    pointRepository.save(point);
    return PointInfo.from(point);
  }

}