package com.concert_reservation.api.business.model.dto.info;

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
public class ConcertOptionInfo {
    private Long concertOptionId;
    private Long concertId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;

    public static ConcertOptionInfo from(ConcertOption concertOption) {
        return ConcertOptionInfo.builder()
            .concertOptionId(concertOption.getConcertOptionId())
            .concertId(concertOption.getConcertId())
            .singerName(concertOption.getSingerName())
            .concertDate(concertOption.getConcertDate())
            .capacity(concertOption.getCapacity())
            .location(concertOption.getLocation())
            .build();
    }

    public ConcertOption toEntity() {
        return ConcertOption.builder()
            .concertOptionId(this.concertOptionId)
            .concertId(this.concertId)
            .singerName(this.singerName)
            .concertDate(this.concertDate)
            .capacity(this.capacity)
            .location(this.location)
            .build();
    }
}
