package com.concert_reservation.api.infrastructure.user;


import com.concert_reservation.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
}