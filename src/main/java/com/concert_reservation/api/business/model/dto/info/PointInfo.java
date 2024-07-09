package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Point;
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
    private Long balance;
    private Long amount;
    private LocalDateTime paymentTime;
    private String paymentMethod;

    public static PointInfo from(Point point) {
        return PointInfo.builder()
            .pointId(point.getPointId())
            .user(UserInfo.from(point.getUser()))
            .balance(point.getBalance())
            .amount(point.getAmount())
            .paymentTime(point.getPaymentTime())
            .paymentMethod(point.getPaymentMethod())
            .build();
    }

    public Point toEntity() {
        return Point.builder()
            .pointId(this.pointId)
            .user(this.user.toEntity())
            .balance(this.balance)
            .amount(this.amount)
            .paymentTime(this.paymentTime)
            .paymentMethod(this.paymentMethod)
            .build();
    }
}