package com.concert_reservation.api.business.model.dto.info;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingInfo {
	private Long bookingId;
	private String userId;
	private Long seatId;
	private LocalDateTime bookingTime;
	private boolean isConfirmed;

	public static BookingInfo from(Booking booking) {
		return BookingInfo.builder()
			.bookingId(booking.getBookingId())
			.userId(booking.getUser().getUserId())
			.seatId(booking.getSeat().getSeatId())
			.bookingTime(booking.getBookingTime())
			.isConfirmed(booking.isConfirmed())
			.build();
	}
}