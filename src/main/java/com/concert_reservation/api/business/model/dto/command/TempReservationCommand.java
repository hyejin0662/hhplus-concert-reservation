package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.TempReservation;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TempReservationCommand {
    private String userId;
    private Long seatId;
    private LocalDateTime tempReservationTime;
    private LocalDateTime expirationTime;

    public TempReservation toEntity(User user, Seat seat) {
        return TempReservation.builder()
                .user(user)
                .seat(seat)
                .tempReservationTime(this.tempReservationTime)
                .expirationTime(this.expirationTime)
                .build();
    }
}
