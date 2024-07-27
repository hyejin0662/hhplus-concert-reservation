package com.concert_reservation.api.interfaces.controller.point.dto;

import java.time.LocalDateTime;

import com.concert_reservation.api.domain.point.PointCommand;
import com.concert_reservation.api.interfaces.controller.user.dto.UserResponse;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {
    class Request {
        private Long bookingId;
        private String userId;
        private String paymentMethod;
        private Long amount;
        private LocalDateTime paymentTime;}
    class Response {
        private Long pointId;
        private Long amount;
        private String paymentMethod;
        private UserResponse user;
    }
}
