package com.concert_reservation.api.application.queue;

import com.concert_reservation.api.domain.queue.model.Token;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.TokenStatus;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenCommand {
    private Long tokenId;
    private String userId;
    private LocalDateTime waitingAt;
    private LocalDateTime expirationAt;
    private TokenStatus tokenStatus;

    public Token toEntity() {
        return DtoConverter.convert(this, Token.class);
    }
}
