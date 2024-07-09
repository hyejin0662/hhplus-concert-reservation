package com.concert_reservation.api.infrastructure.persistance.impl;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

  private final SeatJpaRepository seatJpaRepository;

  @Override
  public List<Seat> findReservedSeatsBySeatIdIn(List<Long> ids) {
    return seatJpaRepository.findReservedSeatsBySeatIdIn(ids);
  }

  @Override
  public void saveAll(List<Seat> seats) {
    seatJpaRepository.saveAll(seats);
  }

  @Override
  public List<Seat> findSeatsByConcertIdAndSeatIdInAndIsReserved(Long concertId, List<Seat> seatIds,
      boolean isReserved) {
    return seatJpaRepository.findSeatsByConcertIdAndSeatIdInAndIsReserved(concertId,seatIds,isReserved);
  }


  @Override
  public Optional<Seat> findById(Long seatId) {
    return seatJpaRepository.findById(seatId);
  }

  @Override
  public List<Seat> findAll() {
    return seatJpaRepository.findAll();
  }

  @Override
  public Seat save(Seat seat) {
    return seatJpaRepository.save(seat);
  }

  @Override
  public void deleteById(Long seatId) {
    seatJpaRepository.deleteById(seatId);
  }

}
