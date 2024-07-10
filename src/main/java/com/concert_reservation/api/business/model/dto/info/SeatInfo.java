package com.concert_reservation.api.business.model.dto.info;

import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.business.model.entity.Seat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatInfo {
    private Long seatId;
    private String concertId;
    private int seatNumber;
    private boolean isReserved;

    public static SeatInfo from(Seat seat) {
        return SeatInfo.builder()
            .seatId(seat.getSeatId())
            .concertId(seat.getConcertId())
            .seatNumber(seat.getSeatNumber())
            .isReserved(seat.isReserved())
            .build();
    }

    public Seat toEntity() {
        return Seat.builder()
            .seatId(this.seatId)
            .concertId(this.concertId)
            .seatNumber(this.seatNumber)
            .isReserved(this.isReserved)
            .build();
    }
}
