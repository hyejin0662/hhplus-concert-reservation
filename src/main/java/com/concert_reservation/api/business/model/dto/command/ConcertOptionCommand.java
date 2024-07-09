package com.concert_reservation.api.business.model.dto.command;

import java.time.LocalDateTime;

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
public class ConcertOptionCommand {
    private Long concertOptionId;
    private String singerName;
    private LocalDateTime concertDate;
    private Long capacity;
    private String location;
}