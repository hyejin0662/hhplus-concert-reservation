package com.concert_reservation.api.infrastructure.persistance.impl;

import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

  @Override
  public Optional<User> findById(String userId) {
    return userJpaRepository.findById(userId);
  }

  @Override
  public Optional<Object> findByConcertCode(String concertCode) {
    return Optional.empty();
  }

  @Override
  public User save(User user) {
    return userJpaRepository.save(user);
  }

  @Override
  public void deleteById(String userId) {
    userJpaRepository.deleteById(userId);
  }

}
