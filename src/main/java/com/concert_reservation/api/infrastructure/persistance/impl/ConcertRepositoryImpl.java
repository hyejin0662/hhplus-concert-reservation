package com.concert_reservation.api.infrastructure.persistance.impl;

import java.time.LocalDate;
import java.util.List;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ConcertJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

  private final ConcertJpaRepository concertJpaRepository;


  @Override
  public List<Concert> findAll() {
    return concertJpaRepository.findAll();
  }

  @Override
  public Concert findById(Long concertId) {
    return concertJpaRepository.findById(concertId).orElse(null);
  }

  @Override
  public List<Seat> findAvailableSeatsByConcertId(Long concertId, LocalDate date) {
    // 실제 좌석 정보를 가져오는 쿼리를 구현해야 합니다.
    return concertJpaRepository.findAvailableSeatsByConcertIdAndDate(concertId, date);
  }
}


