package com.concert_reservation.api.application.facade;

import com.concert_reservation.api.business.service.impl.ConcertServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

  private final ConcertServiceImpl concertServiceImpl;

}
