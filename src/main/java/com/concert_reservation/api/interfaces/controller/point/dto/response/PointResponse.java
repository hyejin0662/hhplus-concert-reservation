package com.concert_reservation.api.interfaces.controller.point.dto.response;

import com.concert_reservation.api.interfaces.controller.user.dto.UserResponse;
import com.concert_reservation.api.application.point.PointInfo;
import com.concert_reservation.common.mapper.DtoConverter;

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
    private Long amount;
    private String paymentMethod;
    private UserResponse user;

    public static PointResponse from(PointInfo point) {
        return DtoConverter.convert(point,PointResponse.class);
    }

}
