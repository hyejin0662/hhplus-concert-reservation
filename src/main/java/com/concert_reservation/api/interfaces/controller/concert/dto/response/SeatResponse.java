package com.concert_reservation.api.interfaces.controller.concert.dto.response;

import com.concert_reservation.api.application.concert.SeatInfo;
import com.concert_reservation.api.domain.concert.model.Seat;
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
public class SeatResponse {
    private Long seatId;
    private int seatNumber;
    private boolean isReserved;
//    private String concertId;
    private ConcertResponse concertResponse;

    public static SeatResponse from(SeatInfo seatInfo) {
        return DtoConverter.convert(seatInfo,SeatResponse.class);
    }

    public Seat toEntity() {
        return DtoConverter.convert(this, Seat.class);
    }
}
