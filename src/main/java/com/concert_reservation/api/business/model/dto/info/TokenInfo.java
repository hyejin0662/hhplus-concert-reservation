package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.common.type.TokenStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenInfo {
    private Long tokenId;
    private UserInfo user;
    private LocalDateTime waitingAt;
    private LocalDateTime expirationAt;
    private TokenStatus tokenStatus;

    public static TokenInfo from(Token token) {
        return TokenInfo.builder()
            .tokenId(token.getTokenId())
            .user(UserInfo.from(token.getUser()))
            .waitingAt(token.getWaitingAt())
            .expirationAt(token.getExpirationAt())
            .tokenStatus(token.getTokenStatus())
            .build();
    }

    public Token toEntity() {
        return Token.builder()
            .tokenId(this.tokenId)
            .user(this.user.toEntity())
            .waitingAt(this.waitingAt)
            .expirationAt(this.expirationAt)
            .tokenStatus(this.tokenStatus)
            .build();
    }
}