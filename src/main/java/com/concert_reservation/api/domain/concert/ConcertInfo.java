package com.concert_reservation.api.domain.concert;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertInfo {
    private Long concertId;
    private String concertName;

    public static ConcertInfo from(Concert concert) {
        return ConcertInfo.builder()
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
