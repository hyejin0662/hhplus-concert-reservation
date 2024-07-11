package com.concert_reservation.api.business.model.dto.command;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.ConcertOption;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertOptionCommand {
    private Long concertOptionId;
    private String concertId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;

    public ConcertOption toEntity() {
        return DtoConverter.convert(this, ConcertOption.class);
    }
}