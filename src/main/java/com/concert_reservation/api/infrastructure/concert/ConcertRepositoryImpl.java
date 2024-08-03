package com.concert_reservation.api.infrastructure.concert;

import java.util.List;

import com.concert_reservation.api.domain.concert.model.Concert;
import com.concert_reservation.api.domain.concert.ConcertRepository;

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
  public Optional<Concert> getConcert(Long concertId) {
    return concertJpaRepository.findById(concertId);
  }


  // @Override
  // public List<Seat> findAvailableSeatsByConcertId(Long concertId, LocalDate date) {
  //   return concertJpaRepository.findAvailableSeatsByConcertIdAndDate(concertId, date);
  // }

  @Override
  public Concert save(Concert concert) {
    return concertJpaRepository.save(concert);
  }

  @Override
  public void deleteById(Long concertId) {
    concertJpaRepository.deleteById(concertId);
  }


}

