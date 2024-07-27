package com.concert_reservation.api.domain.queue;

import com.concert_reservation.api.domain.queue.WaitingCount;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingCountCommand {
    private Long countId;
    private Long tokenId;
    private Long count;

    public WaitingCount toEntity() {
        return DtoConverter.convert(this, WaitingCount.class);
    }
}
