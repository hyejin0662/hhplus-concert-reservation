package com.concert_reservation.api.infrastructure.persistance.orm;


import com.concert_reservation.api.business.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
}