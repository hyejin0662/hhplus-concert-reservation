package com.concert_reservation.api.domain.concert;

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
        return SeatInfo.builder()
            .seatId(seat.getSeatId())
            .seatNumber(seat.getSeatNumber())
            .isReserved(seat.isReserved())
            .build();
    }

    public Seat toEntity() {
        return Seat.builder()
            .seatId(this.seatId)
            .seatNumber(this.seatNumber)
            .isReserved(this.isReserved)
            .build();
    }
}
