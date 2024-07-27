package com.concert_reservation.api.domain.queue;

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
    private Long countId;
    private Long count;

    public static WaitingCountInfo from(WaitingCount waitingCount) {
        return WaitingCountInfo.builder()
            .countId(waitingCount.getCountId())
            .count(waitingCount.getCount())
            .build();
    }

    public WaitingCount toEntity() {
        return WaitingCount.builder()
            .countId(this.countId)
            .count(this.count)
            .build();
    }
}
