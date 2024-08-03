package com.concert_reservation.api.application.concert;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.concert.model.ConcertOption;
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
public class ConcertOptionInfo {
    private Long concertOptionId;
    private Long concertId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;

    public static ConcertOptionInfo from(ConcertOption concertOption) {
        return DtoConverter.convert(concertOption,ConcertOptionInfo.class);
    }

    public ConcertOption toEntity() {
        return DtoConverter.convert(this,ConcertOption.class);
    }
}
