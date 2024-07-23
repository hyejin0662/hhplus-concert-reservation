package com.concert_reservation.api.infrastructure.persistance.impl;

import static com.concert_reservation.common.type.TokenStatus.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.TokenJpaRepository;
import com.concert_reservation.common.type.TokenStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

  private final TokenJpaRepository tokenJpaRepository;

  @Override
  public Token save(Token token) {
    return tokenJpaRepository.save(token);
  }

  @Override
  public Optional<Token> findByUserId(String userID) {
    return tokenJpaRepository.findByUserUserId(userID);
  }


  @Override
  public Optional<Token> findById(Long tokenId) {
    return tokenJpaRepository.findById(tokenId);
  }

  @Override
  public List<Token> findAll() {
    return tokenJpaRepository.findAll();
  }

  @Override
  public void deleteById(Long tokenId) {
    tokenJpaRepository.deleteById(tokenId);
  }

  @Override
  public List<Token> findAllByTokenStatusAndExpirationAtBefore(TokenStatus tokenStatus, LocalDateTime now) {
    return tokenJpaRepository.findAllByTokenStatusAndExpirationAtBefore(tokenStatus, now);

  }

  @Override
  public List<Token> saveAll(List<Token> tokensToBeExpired) {
    return tokenJpaRepository.saveAll(tokensToBeExpired);
  }

  @Override
  public int countByTokenStatusAndWaitingAtBefore(TokenStatus tokenStatus, LocalDateTime waitingAt) {
    return tokenJpaRepository.countByTokenStatusAndWaitingAtBefore(tokenStatus, waitingAt);
  }

  @Override
  public void deleteByUserId(String userId) {
    tokenJpaRepository.deleteByUserUserId(userId);
  }

  @Override
  public Optional<Token> getFirstWaitingToken() {
    return tokenJpaRepository.findFirstByTokenStatusOrderByWaitingAtAsc(WAITING);
  }
}
