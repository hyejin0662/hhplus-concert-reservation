package com.concert_reservation.api.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.model.entity.Concert;

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
