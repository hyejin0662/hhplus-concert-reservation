package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.business.repo.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl {

  private final ConcertRepository concertRepository;

}
