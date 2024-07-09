package com.concert_reservation.api.infrastructure.persistance.impl;


import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.BookingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

  private final BookingJpaRepository bookingJpaRepository;


  @Override
  public Booking save(Booking booking) {
    return bookingJpaRepository.save(booking);
  }

  @Override
  public List<Booking> saveAll(List<Booking> bookings) {
    return bookingJpaRepository.saveAll(bookings);
  }

  @Override
  public Optional<Booking> findById(Long bookingId) {
    return bookingJpaRepository.findById(bookingId);
  }

  @Override
  public List<Booking> findAll() {
    return bookingJpaRepository.findAll();
  }

  @Override
  public void deleteById(Long bookingId) {
    bookingJpaRepository.deleteById(bookingId);
  }

}

