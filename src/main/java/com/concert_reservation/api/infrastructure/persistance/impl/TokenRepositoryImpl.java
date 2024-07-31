package com.concert_reservation.api.infrastructure.persistance.impl;

import static com.concert_reservation.common.token.QueueTokenGenerator.*;
import static com.concert_reservation.common.type.TokenStatus.*;
import static java.lang.Boolean.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.domain.WaitingToken;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.TokenJpaRepository;
import com.concert_reservation.common.type.RedisKeys;
import com.concert_reservation.common.type.TokenStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

  private final TokenJpaRepository tokenJpaRepository;
  // private final RedisTemplate redisTemplate;
  private final RedisTemplate<String, Object> redisTemplate;
  private static final String TOKEN_PREFIX = RedisKeys.TOKEN_KEY_PREFIX.getKey();
  private static final String QUEUE_KEY = RedisKeys.QUEUE_KEY.getKey();

  @Override
  public Token save(Token token) {
    // rdb 부분
    return tokenJpaRepository.save(token);
  }

  @Override
  public WaitingToken enqueue(String userId){
    String tokenValue = generateToken(userId);
    Boolean result = redisTemplate.opsForZSet().add(QUEUE_KEY, tokenValue, System.currentTimeMillis());

    if (FALSE.equals(result) || result == null){
      throw new RuntimeException();
    }

    return WaitingToken.of(userId, tokenValue, result);
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

  @Override
  public Long getTokenRank(String userId) {
    String tokenValue = generateToken(userId);
    return redisTemplate.opsForZSet().rank(QUEUE_KEY, tokenValue);
  }

}
