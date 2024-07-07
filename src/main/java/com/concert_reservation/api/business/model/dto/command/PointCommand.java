package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.api.business.model.entity.User;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PointCommand {
    private String userId;
    private Long amount;

    public Point toEntity(User user) {
        return Point.builder()
                .user(user)
                .amount(this.amount)
                .build();
    }
}
