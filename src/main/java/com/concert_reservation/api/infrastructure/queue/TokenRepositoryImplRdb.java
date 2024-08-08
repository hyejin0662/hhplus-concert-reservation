package com.concert_reservation.api.infrastructure.queue;// package com.concert_reservation.api.infrastructure.persistance.impl;
//
// import static com.concert_reservation.common.token.QueueTokenGenerator.*;
// import static com.concert_reservation.common.type.RedisKeys.*;
// import static com.concert_reservation.common.type.TokenStatus.*;
// import static java.lang.Boolean.*;
//
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;
// import java.util.Set;
// import java.util.stream.Collectors;
//
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Repository;
//
// import com.concert_reservation.api.business.model.domain.ActiveToken;
// import com.concert_reservation.api.business.model.domain.WaitingToken;
// import com.concert_reservation.api.business.model.entity.Token;
// import com.concert_reservation.api.business.repo.TokenRepository;
// import com.concert_reservation.api.infrastructure.persistance.orm.TokenJpaRepository;
// import com.concert_reservation.common.type.RedisKeys;
// import com.concert_reservation.common.type.TokenStatus;
//
// import lombok.RequiredArgsConstructor;
//
// @Repository
// @RequiredArgsConstructor
// public class TokenRepositoryImplRdb implements TokenRepository {
//
//   private final TokenJpaRepository tokenJpaRepository;
//
//   private final RedisTemplate<String, Object> redisTemplate;
//   private static final String TOKEN_PREFIX = RedisKeys.TOKEN_KEY_PREFIX.getKey();
//   private static final String QUEUE_KEY = RedisKeys.QUEUE_KEY.getKey();
//
//   @Override
//   public Token save(Token token) {
//     // rdb 부분
//     return tokenJpaRepository.save(token);
//   }
//
//   @Override
//   public Optional<Token> findByUserId(String userID) {
//     return tokenJpaRepository.findByUserUserId(userID);
//   }
//
//
//   @Override
//   public Optional<Token> findById(Long tokenId) {
//     return tokenJpaRepository.findById(tokenId);
//   }
//
//   @Override
//   public List<Token> findAll() {
//     return tokenJpaRepository.findAll();
//   }
//
//   @Override
//   public void deleteById(Long tokenId) {
//     tokenJpaRepository.deleteById(tokenId);
//   }
//
//   @Override
//   public List<Token> findAllByTokenStatusAndExpirationAtBefore(TokenStatus tokenStatus, LocalDateTime now) {
//     return tokenJpaRepository.findAllByTokenStatusAndExpirationAtBefore(tokenStatus, now);
//
//   }
//
//   @Override
//   public List<Token> saveAll(List<Token> tokensToBeExpired) {
//     return tokenJpaRepository.saveAll(tokensToBeExpired);
//   }
//
//   @Override
//   public int countByTokenStatusAndWaitingAtBefore(TokenStatus tokenStatus, LocalDateTime waitingAt) {
//     return tokenJpaRepository.countByTokenStatusAndWaitingAtBefore(tokenStatus, waitingAt);
//   }
//
//   @Override
//   public void deleteByUserId(String userId) {
//     tokenJpaRepository.deleteByUserUserId(userId);
//   }
//
//   @Override
//   public Optional<Token> getFirstWaitingToken() {
//     return tokenJpaRepository.findFirstByTokenStatusOrderByWaitingAtAsc(WAITING);
//   }
//
//   @Override
//   public WaitingToken enqueue(String userId){
//     String tokenValue = generateToken(userId);
//     Boolean result = redisTemplate.opsForZSet().add(QUEUE_KEY, tokenValue, System.currentTimeMillis());
//
//     if (FALSE.equals(result) || result == null){
//       throw new RuntimeException();
//     }
//
//     return WaitingToken.of(userId, tokenValue, result);
//   }
//
//   /**
//    * redis 대기열 처리 순서
//    */
//   @Override
//   public Long getTokenRank(String userId) {
//     String tokenValue = generateToken(userId);
//     return redisTemplate.opsForZSet().rank(QUEUE_KEY, tokenValue);
//   }
//
//   /**
//    * redis 별도 Counter
//    */
//   @Override
//   public void incrementCounter() {
//     redisTemplate.opsForValue().increment(String.valueOf(COUNTER_KEY));  // 1개씩 올라가는가 ? 확인
//   }
//
//   @Override
//   public void decrementCounter() {
//     redisTemplate.opsForValue().decrement(String.valueOf(COUNTER_KEY));
//   }
//
//   @Override
//   public long getActiveTokenCount() {
//     // todo -> 처리열은 key value로 저장되고 별도 카운터를 통해 계수하므로 카운터 값을 가져와서 확인 (완)
//     // todo -> counter를 만들고 카운터로 계수하여 현재 카운트 값이 몇인지 반환하는 로직 구현 (완)
//     Long counterValue = (Long)redisTemplate.opsForValue().get(COUNTER_KEY.getKey());
//     return counterValue != null ? counterValue : 0;
//   }
//
//   @Override
//   public Optional<String> getFirstTokenFromQueue() {
//     Set<Object> tokens = redisTemplate.opsForZSet().range(QUEUE_KEY, 0, 0);
//     if (tokens == null || tokens.isEmpty()) {
//       return Optional.empty();
//     }
//     return Optional.of(tokens.iterator().next().toString());
//   }
//
//   @Override
//   public void transfer(WaitingToken token) {
//     redisTemplate.opsForZSet().remove(QUEUE_KEY, token.getTokenValue());
//
//     redisTemplate.opsForSet().add(String.valueOf(PROCESSING_KEY), token, System.currentTimeMillis());
//   }
//
//   @Override
//   public void removeFromProcessingQueue(String token) {
//     redisTemplate.opsForZSet().remove(String.valueOf(PROCESSING_KEY), token);
//   }
//
//   // 대기열에서 토큰 제거 메서드
//   @Override
//   public void removeFromQueue(String token) {
//     redisTemplate.opsForZSet().remove(QUEUE_KEY, token);
//   }
//
//   // Redis에 토큰 상태 저장 메서드 추가
//   @Override
//   public void saveTokenToRedis(Token token) {
//     String tokenKey = TOKEN_PREFIX + token.getTokenId();
//     redisTemplate.opsForValue().set(tokenKey, token);
//   }
//
//   // 처리열에 토큰이 존재하는지 확인하는 메서드 추가
//   @Override
//   public boolean isActive(String token) {
//     // todo ->처리열은 key value로 저장하므로  token value를 key값으로 한 key-value가 존재한다면 true (완)
//
//     Long rank = redisTemplate.opsForZSet().rank(String.valueOf(PROCESSING_KEY), token);
//     return rank != null;
//   }
//
//   @Override
//   public List<WaitingToken> getTopWaitingTokens(long activeTokenCount) {
//     // todo -> waiting tokens = sortedset에서 가장 rank가 높은 거 activeTokenCount개수 만큼 조회해서 그 token value를 갖고 token 객체 만들어서 리턴
//     Set<Object> tokens = redisTemplate.opsForZSet().range(QUEUE_KEY, 0, activeTokenCount - 1);
//     if (tokens == null || tokens.isEmpty()) {
//       return List.of();
//     }
//     return tokens.stream().map(token -> new WaitingToken(token.toString())).collect(Collectors.toList());
//   }
//
//   // TODO : 위아래 이따물어보기
//   @Override
//   public List<ActiveToken> getTokens() {
//
//     // todo -> PROCESSING_KEY 로해서 active 토큰 넣은 것들을 전부 가져와서 리턴(완료)
//     Set<Object> tokens = redisTemplate.opsForZSet().range(String.valueOf(PROCESSING_KEY), 0, -1);
//     if (tokens == null || tokens.isEmpty()) {
//       return List.of();
//     }
//     return tokens.stream().map(token -> new ActiveToken(token.toString())).collect(Collectors.toList());
//   }
//
// }
