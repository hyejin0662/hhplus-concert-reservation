package com.concert_reservation.api.business.model.dto.info;

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
public class WaitingCountInfo {
    private Long contId;
    private TokenInfo token;
    private UserInfo user;
    private Long count;

    public static WaitingCountInfo from(WaitingCount waitingCount) {
        return WaitingCountInfo.builder()
            .contId(waitingCount.getContId())
            .token(TokenInfo.from(waitingCount.getToken()))
            .user(UserInfo.from(waitingCount.getUser()))
            .count(waitingCount.getCount())
            .build();
    }

    public WaitingCount toEntity() {
        return WaitingCount.builder()
            .contId(this.contId)
            .token(this.token.toEntity())
            .user(this.user.toEntity())
            .count(this.count)
            .build();
    }
}