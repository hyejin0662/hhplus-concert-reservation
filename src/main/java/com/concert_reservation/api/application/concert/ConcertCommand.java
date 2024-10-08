package com.concert_reservation.api.application.concert;

import com.concert_reservation.api.domain.concert.model.Concert;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertCommand {
    private Long concertId;
    private String concertName;

    public Concert toEntity() {
        return DtoConverter.convert(this, Concert.class);
    }
}