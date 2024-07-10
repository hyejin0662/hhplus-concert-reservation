package com.concert_reservation.api.business.model.dto.info;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.common.type.BookingStatus;

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
	private String userId;
	private Long seatId;
	private LocalDateTime bookingTime;
	private BookingStatus bookingStatus;

	public static BookingInfo from(Booking booking) {
		return BookingInfo.builder()
			.bookingId(booking.getBookingId())
			.userId(booking.getUserId())
			.seatId(booking.getSeatId())
			.bookingTime(booking.getBookingTime())
			.bookingStatus(booking.getBookingStatus())
			.build();
	}

	public Booking toEntity() {
		return Booking.builder()
			.bookingId(this.bookingId)
			.userId(this.userId)
			.seatId(this.seatId)
			.bookingTime(this.bookingTime)
			.bookingStatus(this.bookingStatus)
			.build();
	}
}
