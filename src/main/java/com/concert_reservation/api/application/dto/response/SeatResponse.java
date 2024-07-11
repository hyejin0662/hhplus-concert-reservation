package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import com.concert_reservation.api.business.model.entity.Seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private Long seatId;
    private int seatNumber;
    private boolean isReserved;
//    private String concertId;
    private ConcertResponse concertResponse;

    public static SeatResponse from(SeatInfo seatInfo) {
        return SeatResponse.builder()
            .seatId(seatInfo.getSeatId())
            .seatNumber(seatInfo.getSeatNumber())
            .isReserved(seatInfo.isReserved())
            .concertResponse(ConcertResponse.from(seatInfo.getConcertInfo()))
            .build();
    }

    public Seat toEntity() {
        return Seat.builder()
            .seatId(this.seatId)
            .seatNumber(this.seatNumber)
            .isReserved(this.isReserved)
            .concert(this.concertResponse.toEntity())
            .build();
    }
}
