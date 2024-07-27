package com.concert_reservation.api.domain.concert;

import com.concert_reservation.api.domain.concert.Seat;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatCommand {
    private Long concertOptionId;

    public Seat toEntity() {
        return DtoConverter.convert(this, Seat.class);
    }
}