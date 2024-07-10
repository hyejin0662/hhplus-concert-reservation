package com.concert_reservation.api.application.dto.response;

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
    private String concertId;

    public static SeatResponse from(Long seatId) {
        return SeatResponse.builder()
            .seatId(seatId)
            .build();
    }

    public Seat toEntity() {
        return Seat.builder()
            .seatId(this.seatId)
            .seatNumber(this.seatNumber)
            .isReserved(this.isReserved)
            .concertId(this.concertId)
            .build();
    }
}
