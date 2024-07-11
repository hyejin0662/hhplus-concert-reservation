package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Concert;
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
