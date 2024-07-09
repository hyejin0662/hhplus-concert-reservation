package com.concert_reservation.api.infrastructure.persistance.impl;

import java.time.LocalDate;
import java.util.List;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ConcertJpaRepository;
import java.util.Optional;
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
  public Optional<Concert> findById(Long concertId) {
    return concertJpaRepository.findById(concertId);
  }

  @Override
  public List<Seat> findAvailableSeatsByConcertId(Long concertId, LocalDate date) {
    return concertJpaRepository.findAvailableSeatsByConcertIdAndDate(concertId, date);
  }

  @Override
  public Concert save(Concert concert) {
    return concertJpaRepository.save(concert);
  }

  @Override
  public void deleteById(Long concertId) {
    concertJpaRepository.deleteById(concertId);
  }

  @Override
  public List<Concert> findConcerts(ConcertRequest concertRequest) {
    return concertJpaRepository.findConcerts(concertRequest);
  }
}

