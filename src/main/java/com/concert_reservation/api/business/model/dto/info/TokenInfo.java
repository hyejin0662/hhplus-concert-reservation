package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.domain.WaitingToken;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.common.type.TokenStatus;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenInfo {
    private Long tokenId;
    private String userId;
    private LocalDateTime waitingAt;
    private LocalDateTime expirationAt;
    private TokenStatus tokenStatus;
    private int waitingNumber;


    private String tokenValue; // redis 구현으로 추가
    private boolean enqueued; // redis 구현으로 추가
    private Long rank; // redis 구현으로 추가

    public static TokenInfo from(Token token) {
        return TokenInfo.builder()
            .tokenId(token.getTokenId())
            .userId(token.getUser().getUserId())
            .waitingAt(token.getWaitingAt())
            .expirationAt(token.getExpirationAt())
            .tokenStatus(token.getTokenStatus())
            .waitingNumber(token.getWaitingNumber())
            .build();
    }

    public static TokenInfo from(WaitingToken enqueue) {
        return TokenInfo.builder().userId(enqueue.getUserId()).tokenValue(enqueue.getTokenValue()).enqueued(
            enqueue.isEnqueued()).build();
    }

	public Token toEntity() {
        User user = new User();
        user.setUserId(this.userId);

        return Token.builder()
            .tokenId(this.tokenId)
            .user(user)
            .waitingAt(this.waitingAt)
            .expirationAt(this.expirationAt)
            .tokenStatus(this.tokenStatus)
            .waitingNumber(this.getWaitingNumber())
            .build();
    }
}
