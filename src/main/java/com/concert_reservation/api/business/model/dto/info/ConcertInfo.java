package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Concert;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertInfo {
    private Long concertId;
    private String name;
    private LocalDateTime date;

    public static ConcertInfo from(Concert concert) {
        return ConcertInfo.builder()
                .concertId(concert.getConcertId())
                .name(concert.getName())
                .date(concert.getDate())
                .build();
    }
}
