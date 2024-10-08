package com.concert_reservation.api.application.concert;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.concert.model.ConcertOption;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertOptionCommand {
    private Long concertOptionId;
    private Long concertId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;

    public ConcertOption toEntity() {
        return DtoConverter.convert(this, ConcertOption.class);
    }
}