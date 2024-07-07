package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.common.type.TokenStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenCommand {
    private String userId;
    private String concertCode;
    private LocalDateTime waitingAt;
    private LocalDateTime expirationAt;
    private TokenStatus tokenStatus;

    public Token toEntity(User user) {
        return Token.builder()
            .user(user)
            .concertCode(this.concertCode)
            .waitingAt(this.waitingAt)
            .expirationAt(this.expirationAt)
            .tokenStatus(this.tokenStatus)
            .build();
    }
}