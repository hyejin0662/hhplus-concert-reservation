package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Token;
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
    private Long waitingCountId;

    public static TokenInfo from(Token token) {
        return TokenInfo.builder()
            .tokenId(token.getTokenId())
            .userId(token.getUserId())
            .waitingAt(token.getWaitingAt())
            .expirationAt(token.getExpirationAt())
            .tokenStatus(token.getTokenStatus())
            .waitingCountId(token.getWaitingCountId())
            .build();
    }

    public Token toEntity() {
        return Token.builder()
            .tokenId(this.tokenId)
            .userId(this.userId)
            .waitingAt(this.waitingAt)
            .expirationAt(this.expirationAt)
            .tokenStatus(this.tokenStatus)
            .waitingCountId(this.waitingCountId)
            .build();
    }
}
