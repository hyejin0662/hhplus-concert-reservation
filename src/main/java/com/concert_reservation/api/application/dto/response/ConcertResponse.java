package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

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
    private String name;
    private LocalDateTime date;
    private List<SeatResponse> seats;

    public static ConcertResponse from(Concert concert) {
        return ConcertResponse.builder()
            .concertId(concert.getConcertId())
            .name(concert.getName())
            .date(concert.getDate())
            .seats(concert.getSeats().stream().map(SeatResponse::from).collect(Collectors.toList()))
            .build();
    }

    public Concert toEntity() {
        return Concert.builder()
            .concertId(this.concertId)
            .name(this.name)
            .date(this.date)
            .build();
    }
}