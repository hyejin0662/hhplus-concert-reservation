package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Concert;
import com.concert_reservation.api.business.model.entity.Queue;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class QueueCommand {
    private String userId;
    private Long concertId;
    private LocalDateTime queueTime;
    private int position;

    public Queue toEntity(User user, Concert concert) {
        return Queue.builder()
                .user(user)
                .concert(concert)
                .queueTime(this.queueTime)
                .position(this.position)
                .build();
    }
}
