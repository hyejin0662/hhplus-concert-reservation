package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.dto.info.TempReservationInfo;
import com.concert_reservation.api.business.model.entity.TempReservation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempReservationResponse {
    private Long tempReservationId;
    private LocalDateTime tempReservationTime;
    private LocalDateTime tokenExpirationTime;
    private boolean isConfirmed;
    private UserResponse user;
    private SeatResponse seat;

    public static TempReservationResponse from(TempReservation tempReservation) {
        return TempReservationResponse.builder()
            .tempReservationId(tempReservation.getTempReservationId())
            .tempReservationTime(tempReservation.getTempReservationTime())
            .tokenExpirationTime(tempReservation.getTokenExpirationTime())
            .isConfirmed(tempReservation.isConfirmed())
            .user(UserResponse.from(tempReservation.getUser()))
            .seat(SeatResponse.from(tempReservation.getSeat()))
            .build();
    }

    public TempReservation toEntity() {
        return TempReservation.builder()
            .tempReservationId(this.tempReservationId)
            .tempReservationTime(this.tempReservationTime)
            .tokenExpirationTime(this.tokenExpirationTime)
            .isConfirmed(this.isConfirmed)
            .user(this.user.toEntity())
            .seat(this.seat.toEntity())
            .build();
    }
}
