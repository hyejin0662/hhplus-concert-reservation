package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.common.type.TokenStatus;

import java.sql.Timestamp;
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
}