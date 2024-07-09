package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.TempReservation;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempReservationCommand {
    private Long tempReservationId;
    private String userId;
    private Long seatId;
    private LocalDateTime tempReservationTime;
    private LocalDateTime tokenExpirationTime;
    private boolean isConfirmed;
}