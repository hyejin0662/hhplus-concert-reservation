package com.concert_reservation.api.business.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concert_reservation.api.application.dto.request.TokenRequest;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.WaitingCount;
import com.concert_reservation.api.business.repo.BookingRepository;
import com.concert_reservation.api.business.repo.ConcertRepository;
import com.concert_reservation.api.business.repo.PointRepository;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.repo.TokenRepository;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.business.repo.WaitingCountRepository;
import com.concert_reservation.api.business.service.WaitingCountService;
import com.concert_reservation.common.type.TokenStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitingCountServiceImpl implements WaitingCountService {

    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final PointRepository pointRepository;
    private final WaitingCountRepository waitingCountRepository;
    private final TokenRepository tokenRepository;

    @Override
    public WaitingCount createWaiting(TokenRequest tokenRequest) {
        Token token = Token.builder()
                .user(userRepository.findById(tokenRequest.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found")))
                .waitingAt(LocalDateTime.now())
                .expirationAt(LocalDateTime.now().plusMinutes(15))
                .tokenStatus(TokenStatus.WAITING)
                .build();

        tokenRepository.save(token);

        WaitingCount waitingCount = WaitingCount.builder()
                .token(token)
                .user(token.getUser())
                .count(waitingCountRepository.countByConcertId(tokenRequest.getConcertId()) + 1)
                .build();

        return waitingCountRepository.saveWaitingCount(waitingCount);
    }


}