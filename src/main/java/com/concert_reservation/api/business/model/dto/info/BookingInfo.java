package com.concert_reservation.api.business.model.dto.info;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.entity.Booking;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	private BookingStatus bookingStatus;

	private LocalDateTime bookingTime;

	public static BookingInfo from(Booking booking) {
		return DtoConverter.convert(booking, BookingInfo.class);
	}
}