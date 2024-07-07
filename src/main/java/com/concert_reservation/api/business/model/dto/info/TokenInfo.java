package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Token;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenInfo {
    private Long tokenId;
    private String userId;
    private String concertCode;
    private LocalDateTime waitingAt;
    private LocalDateTime expirationAt;
    private String tokenStatus;

    public static TokenInfo from(Token token) {
        return TokenInfo.builder()
            .tokenId(token.getTokenId())
            .userId(token.getUser().getUserId())
            .concertCode(token.getConcertCode())
            .waitingAt(token.getWaitingAt())
            .expirationAt(token.getExpirationAt())
            .tokenStatus(token.getTokenStatus().name())
            .build();
    }
}
