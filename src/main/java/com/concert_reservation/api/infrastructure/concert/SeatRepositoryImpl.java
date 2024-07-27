package com.concert_reservation.api.infrastructure.concert;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.domain.concert.Seat;
import com.concert_reservation.api.domain.concert.SeatRepository;
import com.concert_reservation.api.infrastructure.concert.SeatJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

  private final SeatJpaRepository seatJpaRepository;


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

  @Override
  public Seat findSeat(Long seatId) {
    return seatJpaRepository.findSeatBySeatId(seatId);
  }

  @Override
  public void saveAll(List<Seat> seats) {
    seatJpaRepository.saveAll(seats);
  }

  @Override
  public Optional<Seat> findByIdWithLock(Long seatId) {
    return seatJpaRepository.findByIdWithLock(seatId);
  }

}
