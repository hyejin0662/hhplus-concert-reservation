package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.common.mapper.DtoConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointInfo {
    private Long pointId;
    private UserInfo user;
    private Long amount;
    private LocalDateTime paymentTime;
    private String paymentMethod;

    public static PointInfo from(Point point) {
        return DtoConverter.convert(point,PointInfo.class);
    }
}
