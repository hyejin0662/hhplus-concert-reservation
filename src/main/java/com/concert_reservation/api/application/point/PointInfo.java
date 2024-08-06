package com.concert_reservation.api.application.point;

import com.concert_reservation.api.domain.point.model.Point;
import com.concert_reservation.api.application.user.UserInfo;
import com.concert_reservation.common.mapper.DtoConverter;

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
