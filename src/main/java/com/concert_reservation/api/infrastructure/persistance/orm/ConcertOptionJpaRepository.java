package com.concert_reservation.api.infrastructure.persistance.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concert_reservation.api.business.model.entity.ConcertOption;

@Repository
public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOption, Long> {
}