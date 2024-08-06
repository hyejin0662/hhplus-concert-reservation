package com.concert_reservation.api.infrastructure.user;

import java.util.Optional;

import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.api.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

  @Override
  public Optional<User> getUser(String userId) {
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
