package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
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
}