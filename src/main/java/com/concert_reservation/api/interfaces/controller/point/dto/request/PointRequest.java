package com.concert_reservation.api.interfaces.controller.point.dto.request;

import java.time.LocalDateTime;

import com.concert_reservation.api.application.point.PointCommand;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointRequest {
    private Long bookingId;
    private String userId;
    private String paymentMethod;
    private Long amount;
    private LocalDateTime paymentTime;

    public PointCommand toCommand() {
        return DtoConverter.convert(this, PointCommand.class);
    }
}
