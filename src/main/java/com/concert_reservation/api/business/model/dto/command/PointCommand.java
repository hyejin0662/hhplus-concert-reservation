package com.concert_reservation.api.business.model.dto.command;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Point;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointCommand {
    private Long pointId;
    private String userId;
    private Long balance;
    private Long amount;
    private LocalDateTime paymentTime;
    private String paymentMethod;

    public Point toEntity() {
        return DtoConverter.convert(this, Point.class);
    }
}