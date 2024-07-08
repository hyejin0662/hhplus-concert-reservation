package com.concert_reservation.api.infrastructure.persistance.impl;


import java.util.List;

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
  public int saveAll(List<Booking> bookings) {
    return 0;
  }

}

