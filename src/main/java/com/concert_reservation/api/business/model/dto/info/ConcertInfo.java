package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Concert;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertInfo {
    private Long concertId;
    private String concertName;
    private List<SeatInfo> seats;
    private List<ConcertOptionInfo> concertOptions;

    public static ConcertInfo from(Concert concert) {
        return ConcertInfo.builder()
            .concertId(concert.getConcertId())
            .concertName(concert.getConcertName())
            .seats(concert.getSeats().stream().map(SeatInfo::from).collect(Collectors.toList()))
            .concertOptions(concert.getConcertOptions().stream().map(ConcertOptionInfo::from).collect(Collectors.toList()))
            .build();
    }

    public Concert toEntity() {
        return Concert.builder()
            .concertId(this.concertId)
            .concertName(this.concertName)
            .build();
    }
}