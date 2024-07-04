package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenCommand {
    private String userId;
    private String concertCode;
    private Timestamp expirationTime;

    public Token toEntity(User user) {
        return Token.builder()
                .user(user)
                .concertCode(this.concertCode)
                .expirationTime(this.expirationTime)
                .build();
    }
}
