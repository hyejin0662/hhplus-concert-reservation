package com.concert_reservation.api.interfaces.controller.queue.dto;

import com.concert_reservation.api.domain.queue.WaitingCountInfo;

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
