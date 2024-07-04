package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Token;
import java.sql.Timestamp;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenInfo {
    private Long tokenId;
    private String userId;
    private String concertCode;
    private Timestamp expirationTime;

    public static TokenInfo from(Token token) {
        return TokenInfo.builder()
                .tokenId(token.getTokenId())
                .userId(token.getUser().getUserId())
                .concertCode(token.getConcertCode())
                .expirationTime(token.getExpirationTime())
                .build();
    }
}
