package com.concert_reservation.api.application.concert;

import com.concert_reservation.api.domain.concert.model.Concert;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertInfo {
    private Long concertId;
    private String concertName;

    public static ConcertInfo from(Concert concert) {
        return DtoConverter.convert(concert, ConcertInfo.class);
    }

    public Concert toEntity() {
        return DtoConverter.convert(this,Concert.class);
    }
}
