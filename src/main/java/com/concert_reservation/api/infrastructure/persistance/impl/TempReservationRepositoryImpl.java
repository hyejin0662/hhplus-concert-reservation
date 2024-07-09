package com.concert_reservation.api.infrastructure.persistance.impl;


import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.TempReservation;
import com.concert_reservation.api.business.repo.TempReservationRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.TempReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TempReservationRepositoryImpl implements TempReservationRepository {

  private final TempReservationJpaRepository tempReservationJpaRepository;

  @Override
  public Optional<TempReservation> findById(Long tempReservationId) {
    return tempReservationJpaRepository.findById(tempReservationId);
  }

  @Override
  public List<TempReservation> findAll() {
    return tempReservationJpaRepository.findAll();
  }

  @Override
  public TempReservation save(TempReservation tempReservation) {
    return tempReservationJpaRepository.save(tempReservation);
  }

  @Override
  public void deleteById(Long tempReservationId) {
    tempReservationJpaRepository.deleteById(tempReservationId);
  }
}
