package com.concert_reservation.api.business.model.dto.info;

import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.business.model.entity.Seat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatInfo {
    private Long seatId;
    private ConcertInfo concert;
    private int seatNumber;
    private boolean isReserved;
    private List<BookingInfo> bookings;
    private List<TempReservationInfo> tempReservations;

    public static SeatInfo from(Seat seat) {
        return SeatInfo.builder()
            .seatId(seat.getSeatId())
            .concert(ConcertInfo.from(seat.getConcert()))
            .seatNumber(seat.getSeatNumber())
            .isReserved(seat.isReserved())
            .bookings(seat.getBookings().stream().map(BookingInfo::from).collect(Collectors.toList()))
            .tempReservations(seat.getTempReservations().stream().map(TempReservationInfo::from).collect(Collectors.toList()))
            .build();
    }

    public Seat toEntity() {
        return Seat.builder()
            .seatId(this.seatId)
            .concert(this.concert.toEntity())
            .seatNumber(this.seatNumber)
            .isReserved(this.isReserved)
            .build();
    }
}