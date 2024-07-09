package com.concert_reservation.api.business.model.dto.info;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingInfo {
	private Long bookingId;
	private UserInfo user;
	private SeatInfo seat;
	private LocalDateTime bookingTime;

	public static BookingInfo from(Booking booking) {
		return BookingInfo.builder()
			.bookingId(booking.getBookingId())
			.user(UserInfo.from(booking.getUser()))
			.seat(SeatInfo.from(booking.getSeat()))
			.bookingTime(booking.getBookingTime())
			.build();
	}

	public Booking toEntity() {
		return Booking.builder()
			.bookingId(this.bookingId)
			.user(this.user.toEntity())
			.seat(this.seat.toEntity())
			.bookingTime(this.bookingTime)
			.build();
	}
}