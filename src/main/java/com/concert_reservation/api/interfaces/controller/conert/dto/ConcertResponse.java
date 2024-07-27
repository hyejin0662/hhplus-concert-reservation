package com.concert_reservation.api.interfaces.controller.conert.dto;

import java.util.List;

import com.concert_reservation.api.domain.concert.ConcertInfo;
import com.concert_reservation.api.domain.concert.Concert;

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
public class ConcertResponse {
    private Long concertId;
    private String concertName;
    private List<SeatResponse> seats;

    public static ConcertResponse from(ConcertInfo concert) {
        return ConcertResponse.builder()
            .concertId(concert.getConcertId())
            .concertName(concert.getConcertName())
            .build();
    }

    public Concert toEntity() {
        return Concert.builder()
            .concertId(this.concertId)
            .concertName(this.concertName)
            .build();
    }
}
