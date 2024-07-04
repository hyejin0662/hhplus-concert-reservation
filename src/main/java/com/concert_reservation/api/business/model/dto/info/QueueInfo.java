package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Queue;
import java.sql.Timestamp;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueInfo {
    private Long queueId;
    private String userId;
    private Long concertId;
    private Timestamp queueTime;
    private int position;

    public static QueueInfo from(Queue queue) {
        return QueueInfo.builder()
                .queueId(queue.getQueueId())
                .userId(queue.getUser().getUserId())
                .concertId(queue.getConcert().getConcertId())
                .queueTime(queue.getQueueTime())
                .position(queue.getPosition())
                .build();
    }
}
