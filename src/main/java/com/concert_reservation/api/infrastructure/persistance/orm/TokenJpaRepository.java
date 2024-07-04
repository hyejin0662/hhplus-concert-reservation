package com.concert_reservation.api.infrastructure.persistance.orm;

import com.concert_reservation.api.business.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {
}