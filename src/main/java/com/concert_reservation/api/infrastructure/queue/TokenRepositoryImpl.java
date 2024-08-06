package com.concert_reservation.api.infrastructure.queue;

import static com.concert_reservation.common.token.QueueTokenGenerator.*;
import static com.concert_reservation.common.type.QueuePolicy.*;
import static com.concert_reservation.common.type.RedisKeys.*;
import static java.lang.Boolean.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.concert_reservation.api.domain.queue.model.WaitingToken;
import com.concert_reservation.api.domain.queue.TokenRepository;
import com.concert_reservation.common.type.RedisKeys;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {


  private final RedisTemplate<String, Object> redisTemplate;


  @Override
  public WaitingToken issueToken(String userId){
    String tokenValue = generateToken(userId);
    redisTemplate.opsForZSet().remove(RedisKeys.QUEUE_KEY.getKey(), tokenValue);
    Boolean result = redisTemplate.opsForZSet().add(RedisKeys.QUEUE_KEY.getKey(), tokenValue, System.currentTimeMillis());

    if (FALSE.equals(result) || result == null){
      throw new RuntimeException();
    }

    return WaitingToken.of(userId, tokenValue, result);
  }

  /**
   * redis 대기열 처리 순서
   */
  @Override
  public Long getTokenRank(String tokenValue) {
    return redisTemplate.opsForZSet().rank(RedisKeys.QUEUE_KEY.getKey(), tokenValue);
  }


  /**
   * 처리열의 카운터를 1 증가시킵니다.
   *
   * <p>이 메서드는 Redis의 지정된 키의 값을 1 증가시킵니다.
   * 이를 통해 처리 중인 토큰의 수를 관리합니다.</p>
   */
  @Override
  public void increaseCounter() {
    redisTemplate.opsForValue().increment(String.valueOf(COUNTER_KEY));
  }

  @Override
  public void decrease() {
    redisTemplate.opsForValue().decrement(String.valueOf(COUNTER_KEY));
  }

  /**
   * 현재 처리열에 있는 활성 토큰의 수를 반환합니다.
   *
   * <p>처리열은 key-value 형식으로 저장되며, 별도의 카운터를 통해 토큰 수를 관리합니다.
   * 이 메서드는 카운터 값을 가져와서 현재 활성 토큰의 수를 확인합니다.</p>
   *
   * <p>처리 과정은 다음과 같습니다:</p>
   * <ul>
   *     <li>Redis에서 카운터 값을 가져옵니다.</li>
   *     <li>카운터 값이 존재하지 않으면 0을 반환합니다.</li>
   * </ul>
   *
   * @return 현재 처리열에 있는 활성 토큰의 수
   */
  @Override
  public long getActiveTokens() {
    Long counterValue = (Long)redisTemplate.opsForValue().get(COUNTER_KEY.getKey());
    return counterValue != null ? counterValue : 0;
  }

  @Override
  public void activateTokens(WaitingToken token) {
    redisTemplate.opsForZSet().remove(RedisKeys.QUEUE_KEY.getKey(), token.getTokenValue());
    redisTemplate.opsForValue().set(token.getTokenValue(),String.valueOf(System.currentTimeMillis() + ACTIVE_TOKEN_EXPIRATION.getValue() * 1000) , ACTIVE_TOKEN_EXPIRATION.getValue(), TimeUnit.SECONDS);
  }

  @Override
  public void removeFromProcessingQueue(String token) {
    redisTemplate.delete(generateToken(token));
  }

  /**
   * 주어진 토큰이 처리열에 존재하는지 확인합니다.
   *
   * <p>처리열은 key-value 형식으로 저장되며, 이 메서드는 주어진 토큰 값을 key로 하는
   * key-value가 존재하는지 여부를 확인합니다.</p>
   *
   * <p>처리 과정은 다음과 같습니다:</p>
   * <ul>
   *     <li>Redis의 처리열에서 주어진 토큰 값의 순위를 조회합니다.</li>
   *     <li>토큰 값이 존재하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.</li>
   * </ul>
   *
   * @param token 처리열에서 확인할 토큰 값
   * @return 토큰 값이 처리열에 존재하면 true, 그렇지 않으면 false
   */
  @Override
  public boolean isActive(String token) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(token));
  }

  /**
   * 대기열에서 주어진 수만큼의 최상위 대기 토큰을 조회합니다.
   *
   * <p>이 메서드는 Redis의 sorted set에서 가장 높은 순위를 가진 토큰들을 조회하여
   * 해당 토큰 값으로 {@code WaitingToken} 객체를 생성하여 반환합니다.</p>
   *
   * <p>처리 과정은 다음과 같습니다:</p>
   * <ul>
   *     <li>sorted set에서 주어진 수(activeTokenCount)만큼의 최상위 토큰 값을 조회합니다.</li>
   *     <li>토큰 값이 존재하지 않으면 빈 리스트를 반환합니다.</li>
   *     <li>조회된 토큰 값들을 이용해 {@code WaitingToken} 객체를 생성하여 리스트로 반환합니다.</li>
   * </ul>
   *
   * @param activeTokenCount 조회할 최상위 토큰의 수
   * @return 조회된 {@code WaitingToken} 객체의 리스트
   */
  @Override
  public List<WaitingToken> getTopWaitingTokens(long activeTokenCount) {
    Set<Object> tokens = redisTemplate.opsForZSet().range(RedisKeys.QUEUE_KEY.getKey(), 0, activeTokenCount - 1);
    if (tokens == null || tokens.isEmpty()) {
      return List.of();
    }
    return tokens.stream().map(token -> new WaitingToken(token.toString())).collect(Collectors.toList());
  }




}
