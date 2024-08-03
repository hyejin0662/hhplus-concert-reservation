package com.concert_reservation.api.interfaces.controller.concert.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.application.concert.ConcertOptionInfo;
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
public class ConcertOptionResponse {
    private Long concertOptionId;
    private Long concertId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;

    public static ConcertOptionResponse from(ConcertOptionInfo concertOption) {
        return DtoConverter.convert(concertOption,ConcertOptionResponse.class);
    }

    public ConcertOption toEntity() {
        return DtoConverter.convert(this,ConcertOption.class);
    }
}
