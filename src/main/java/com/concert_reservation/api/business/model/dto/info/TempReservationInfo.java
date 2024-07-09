package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.TempReservation;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempReservationInfo {
    private Long tempReservationId;
    private UserInfo user;
    private SeatInfo seat;
    private LocalDateTime tempReservationTime;
    private LocalDateTime tokenExpirationTime;
    private boolean isConfirmed;

    public static TempReservationInfo from(TempReservation tempReservation) {
        return TempReservationInfo.builder()
            .tempReservationId(tempReservation.getTempReservationId())
            .user(UserInfo.from(tempReservation.getUser()))
            .seat(SeatInfo.from(tempReservation.getSeat()))
            .tempReservationTime(tempReservation.getTempReservationTime())
            .tokenExpirationTime(tempReservation.getTokenExpirationTime())
            .isConfirmed(tempReservation.isConfirmed())
            .build();
    }

    public TempReservation toEntity() {
        return TempReservation.builder()
            .tempReservationId(this.tempReservationId)
            .user(this.user.toEntity())
            .seat(this.seat.toEntity())
            .tempReservationTime(this.tempReservationTime)
            .tokenExpirationTime(this.tokenExpirationTime)
            .isConfirmed(this.isConfirmed)
            .build();
    }
}