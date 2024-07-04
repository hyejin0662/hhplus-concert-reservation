package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.Reservation;
import java.sql.Timestamp;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationInfo {
    private Long reservationId;
    private String userId;
    private Long seatId;
    private Timestamp reservationTime;
    private boolean isConfirmed;

    public static ReservationInfo from(Reservation reservation) {
        return ReservationInfo.builder()
                .reservationId(reservation.getReservationId())
                .userId(reservation.getUser().getUserId())
                .seatId(reservation.getSeat().getSeatId())
                .reservationTime(reservation.getReservationTime())
                .isConfirmed(reservation.isConfirmed())
                .build();
    }
}
