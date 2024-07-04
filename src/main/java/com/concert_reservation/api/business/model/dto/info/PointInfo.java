package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Point;
import java.math.BigDecimal;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointInfo {
    private Long pointId;
    private String userId;
    private BigDecimal amount;

    public static PointInfo from(Point point) {
        return PointInfo.builder()
                .pointId(point.getPointId())
                .userId(point.getUser().getUserId())
                .amount(point.getAmount())
                .build();
    }
}
