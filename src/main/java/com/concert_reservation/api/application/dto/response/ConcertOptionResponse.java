package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.ConcertOption;

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
public class ConcertOptionResponse {
    private Long concertOptionId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;

    public static ConcertOptionResponse from(ConcertOption concertOption) {
        return ConcertOptionResponse.builder()
            .concertOptionId(concertOption.getConcertOptionId())
            .singerName(concertOption.getSingerName())
            .concertDate(concertOption.getConcertDate())
            .capacity(concertOption.getCapacity())
            .location(concertOption.getLocation())
            .build();
    }

    public ConcertOption toEntity() {
        return ConcertOption.builder()
            .concertOptionId(this.concertOptionId)
            .singerName(this.singerName)
            .concertDate(this.concertDate)
            .capacity(this.capacity)
            .location(this.location)
            .build();
    }
}