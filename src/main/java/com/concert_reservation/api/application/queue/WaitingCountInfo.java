package com.concert_reservation.api.application.queue;

import com.concert_reservation.api.domain.queue.model.WaitingCount;
import com.concert_reservation.common.mapper.DtoConverter;

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
        return DtoConverter.convert(waitingCount,WaitingCountInfo.class);
    }

    public WaitingCount toEntity() {
        return DtoConverter.convert(this,WaitingCount.class);
    }
}
