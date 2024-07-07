package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.TempReservation;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempReservationInfo {
    private Long tempReservationId;
    private String userId;
    private Long seatId;
    private LocalDateTime tempReservationTime;
    private LocalDateTime expirationTime;

    public static TempReservationInfo from(TempReservation tempReservation) {
        return TempReservationInfo.builder()
                .tempReservationId(tempReservation.getTempReservationId())
                .userId(tempReservation.getUser().getUserId())
                .seatId(tempReservation.getSeat().getSeatId())
                .tempReservationTime(tempReservation.getTempReservationTime())
                .expirationTime(tempReservation.getExpirationTime())
                .build();
    }
}
