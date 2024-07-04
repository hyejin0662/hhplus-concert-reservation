package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Concert;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ConcertCommand {
    private String name;
    private LocalDateTime date;

    public Concert toEntity() {
        return Concert.builder()
                .name(this.name)
                .date(this.date)
                .build();
    }
}
