package com.concert_reservation.api.infrastructure.persistance.impl;

import java.util.Optional;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ConcertJpaRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.TokenJpaRepository;
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
  public void updateStatusProcessing(Token token) {
    tokenJpaRepository.updateStatusProcessing(token.getTokenId());
  }

  @Override
  public Optional<Token> findFirstByTokenStatusOrderByWaitingAtAsc() {
    return tokenJpaRepository.findFirstByTokenStatusOrderByWaitingAtAsc();
  }

}
