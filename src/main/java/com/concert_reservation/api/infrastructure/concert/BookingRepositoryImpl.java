package com.concert_reservation.api.infrastructure.concert;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.concert_reservation.api.application.concert.BookingInfo;
import com.concert_reservation.api.domain.concert.model.Booking;
import com.concert_reservation.api.domain.concert.BookingRepository;
import com.concert_reservation.common.type.BookingStatus;

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

  @Override
  public Optional<Booking> findByUserId(String userId) {
    return bookingJpaRepository.findByUserUserId(userId);
  }

  @Override
  public List<BookingInfo> findAll(BookingStatus bookingStatus) {
    return bookingJpaRepository.findAllByBookingStatusIs(bookingStatus).stream().map(BookingInfo::from).collect(
        Collectors.toList());
  }

  @Override
  public void deleteByUserId(String userId) {
    bookingJpaRepository.deleteByUserUserId(userId);
  }

}

