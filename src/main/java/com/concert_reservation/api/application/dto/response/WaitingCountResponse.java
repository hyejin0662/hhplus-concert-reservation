package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.dto.info.WaitingCountInfo;
import com.concert_reservation.api.business.model.entity.Token;
import com.concert_reservation.api.business.model.entity.User;
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

    public static WaitingCountResponse from(WaitingCountInfo waitingCount) {
        return WaitingCountResponse.builder()
            .countId(waitingCount.getCountId())
            .count(waitingCount.getCount())
            .build();
    }


}
