package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.business.service.TokenService;
import com.concert_reservation.api.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final TokenRepository tokenRepository;
}

