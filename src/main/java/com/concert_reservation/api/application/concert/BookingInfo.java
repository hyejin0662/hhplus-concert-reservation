package com.concert_reservation.api.application.concert;

import com.concert_reservation.api.domain.concert.model.Booking;
import com.concert_reservation.api.application.user.UserInfo;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import java.time.LocalDateTime;

import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingInfo {
	private Long bookingId;

	private UserInfo user;

	private SeatInfo seat;

	private BookingStatus bookingStatus;

	private LocalDateTime bookingTime;

	public static BookingInfo from(Booking booking) {
		return DtoConverter.convert(booking, BookingInfo.class);
	}

	public void cancel() {
		this.bookingStatus = BookingStatus.CANCELLED;
	}

	public void reserve() {
		this.bookingStatus = BookingStatus.PENDING;
	}

	public void complete() {
		this.bookingStatus = BookingStatus.CONFIRMED;
	}

	public String getUserId() {
		if (this.user == null){
			throw new CustomException(GlobalResponseCode.USER_NOT_FOUND);
		}
		return this.user.getUserId();
	}
}