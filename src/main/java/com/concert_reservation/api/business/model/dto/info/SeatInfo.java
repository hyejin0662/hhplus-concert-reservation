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
//    private String concertId;
    private ConcertInfo concertInfo;
    private int seatNumber;
    private boolean isReserved;

    public static SeatInfo from(Seat seat) {
        return SeatInfo.builder()
            .seatId(seat.getSeatId())
            .concertInfo(ConcertInfo.from(seat.getConcert()))
            .seatNumber(seat.getSeatNumber())
            .isReserved(seat.isReserved())
            .build();
    }

    public Seat toEntity() {
        return Seat.builder()
            .seatId(this.seatId)
            .concert(this.concertInfo.toEntity())
            .seatNumber(this.seatNumber)
            .isReserved(this.isReserved)
            .build();
    }
}
