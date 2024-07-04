package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Seat;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SeatCommand {
    private Long concertId;
    private int seatNumber;
    private boolean isReserved;

    public Seat toEntity(Concert concert) {
        return Seat.builder()
                .concert(concert)
                .seatNumber(this.seatNumber)
                .isReserved(this.isReserved)
                .build();
    }
}
