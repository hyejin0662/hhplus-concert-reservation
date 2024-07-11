package com.concert_reservation.api.infrastructure.persistance.orm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {


}
