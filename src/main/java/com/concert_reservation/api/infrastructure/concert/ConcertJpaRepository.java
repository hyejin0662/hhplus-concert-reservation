package com.concert_reservation.api.infrastructure.concert;

import com.concert_reservation.api.domain.concert.Concert;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {


}
