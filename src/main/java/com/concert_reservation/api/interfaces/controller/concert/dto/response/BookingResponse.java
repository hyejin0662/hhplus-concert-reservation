package com.concert_reservation.api.interfaces.controller.concert.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.interfaces.controller.user.dto.UserResponse;
import com.concert_reservation.api.application.concert.AvailableDatesInfo;
import com.concert_reservation.api.application.concert.BookingInfo;
import com.concert_reservation.api.domain.concert.model.Booking;
import com.concert_reservation.common.mapper.DtoConverter;
import com.concert_reservation.common.type.BookingStatus;
import com.concert_reservation.common.type.ResponseResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

        private ResponseResult responseResult;
        private Long bookingId;
        private BookingStatus bookingStatus;
        private LocalDateTime bookingTime;
        private UserResponse user;
        private SeatResponse seat;

        public static BookingResponse from(BookingInfo bookingInfo) {
                return DtoConverter.convert(bookingInfo, BookingResponse.class);
        }

        public Booking toEntity() {
            return DtoConverter.convert(this,Booking.class);
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class AvailableDates {


                private Long concertOptionId;
                private Long concertId;
                private String singerName;
                private LocalDateTime concertDate;
                private Long capacity;
                private String location;

                public static AvailableDates from(AvailableDatesInfo info){
                        return DtoConverter.convert(info, AvailableDates.class);
                }
        }

}
