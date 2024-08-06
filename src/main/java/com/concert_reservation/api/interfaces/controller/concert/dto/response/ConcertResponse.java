package com.concert_reservation.api.interfaces.controller.concert.dto.response;

import java.util.List;

import com.concert_reservation.api.application.concert.ConcertInfo;
import com.concert_reservation.api.domain.concert.model.Concert;
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
public class ConcertResponse {
    private Long concertId;
    private String concertName;
    private List<SeatResponse> seats;

    public static ConcertResponse from(ConcertInfo concert) {
        return DtoConverter.convert(concert,ConcertResponse.class);
    }

    public Concert toEntity() {
        return DtoConverter.convert(this,Concert.class);
    }
}
