package com.concert_reservation.api.business.service.impl;


import com.concert_reservation.api.business.repo.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl {
  private final PointRepository pointRepository;

}
