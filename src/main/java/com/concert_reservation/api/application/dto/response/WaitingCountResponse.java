package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.entity.WaitingCount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingCountResponse {
    private Long countId;
    private Long count;
    private TokenResponse token;
    private UserResponse user;

    public static WaitingCountResponse from(WaitingCount waitingCount) {
        return WaitingCountResponse.builder()
            .countId(waitingCount.getCountId())
            .count(waitingCount.getCount())
            .token(TokenResponse.from(waitingCount.getToken()))
            .user(UserResponse.from(waitingCount.getUser()))
            .build();
    }

    public WaitingCount toEntity() {
        return WaitingCount.builder()
            .countId(this.countId)
            .count(this.count)
            .token(this.token.toEntity())
            .user(this.user.toEntity())
            .build();
    }
}