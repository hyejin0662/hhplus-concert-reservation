package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Seat;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatInfo {
    private Long seatId;
    private Long concertId;
    private int seatNumber;
    private boolean isReserved;

    public static SeatInfo from(Seat seat) {
        return SeatInfo.builder()
                .seatId(seat.getSeatId())
                .concertId(seat.getConcert().getConcertId())
                .seatNumber(seat.getSeatNumber())
                .isReserved(seat.isReserved())
                .build();
    }
}
