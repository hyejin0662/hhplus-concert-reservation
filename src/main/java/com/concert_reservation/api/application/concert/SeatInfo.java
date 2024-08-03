package com.concert_reservation.api.application.concert;

import com.concert_reservation.api.domain.concert.model.Seat;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatInfo {
    private Long seatId;
    private int seatNumber;
    private boolean isReserved;
    private ConcertInfo concertInfo;

    public static SeatInfo from(Seat seat) {
        return DtoConverter.convert(seat,SeatInfo.class);
    }

    public Seat toEntity() {
        return DtoConverter.convert(this,Seat.class);
    }
}
