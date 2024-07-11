package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.common.mapper.DtoConverter;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatCommand {
    private Long seatId;
    private Long concertId;
    private int seatNumber;
    private boolean isReserved;
    private LocalDateTime date;

    public Seat toEntity() {
        return DtoConverter.convert(this, Seat.class);
    }
}