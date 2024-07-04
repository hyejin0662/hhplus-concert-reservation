package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.Reservation;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.User;
import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationCommand {
    private String userId;
    private Long seatId;
    private Timestamp reservationTime;
    private boolean isConfirmed;

    public Reservation toEntity(User user, Seat seat) {
        return Reservation.builder()
                .user(user)
                .seat(seat)
                .reservationTime(this.reservationTime)
                .isConfirmed(this.isConfirmed)
                .build();
    }
}
