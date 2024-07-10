package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.entity.Point;

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
public class PointResponse {
    private Long pointId;
    private Long balance;
    private Long amount;
    private String paymentMethod;
    private UserResponse user;

    public static PointResponse from(PointInfo point) {
        return PointResponse.builder()
            .pointId(point.getPointId())
            .balance(point.getBalance())
            .amount(point.getAmount())
            .paymentMethod(point.getPaymentMethod())
            // TODO : Required type: UserInfo, Provided: String 라는 오류가 발생합니다. 파라미터 수정해줘.
            .user(UserResponse.from(point.getUserId()))
            .build();
    }

    public Point toEntity() {
        return Point.builder()
            .pointId(this.pointId)
            .balance(this.balance)
            .amount(this.amount)
            .paymentMethod(this.paymentMethod)
            .userId(this.user.getUserId())
            .build();
    }
}
