package com.concert_reservation.api.application.facade;

import com.concert_reservation.api.application.dto.request.BookingRequest;
import com.concert_reservation.api.application.dto.response.BookingResponse;
import com.concert_reservation.api.business.model.dto.command.BookingCommand;
import com.concert_reservation.api.business.model.dto.info.BookingInfo;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.service.impl.BookingServiceImpl;
import com.concert_reservation.api.business.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingFacade {
    private final BookingServiceImpl bookingService;
    private final UserServiceImpl userService;

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        BookingCommand bookingCommand = BookingCommand.builder()
            .userId(bookingRequest.getUserId())
            .seatId(bookingRequest.getSeatId())
            .bookingTime(bookingRequest.getBookingTime())
            .build();
        BookingInfo bookingInfo = bookingService.createBooking(bookingCommand);
        UserInfo userInfo = userService.getUser(bookingInfo.getUserId());
        return BookingResponse.from(bookingInfo, userInfo);
    }

    public BookingResponse getBooking(Long bookingId) {
        BookingInfo bookingInfo = bookingService.getBooking(bookingId);
        UserInfo userInfo = userService.getUser(bookingInfo.getUserId());
        return BookingResponse.from(bookingInfo, userInfo);
    }

    public BookingResponse updateBooking(Long bookingId, BookingRequest bookingRequest) {
        BookingCommand bookingCommand = BookingCommand.builder()
            .bookingId(bookingId)
            .userId(bookingRequest.getUserId())
            .seatId(bookingRequest.getSeatId())
            .bookingTime(bookingRequest.getBookingTime())
            .build();
        BookingInfo bookingInfo = bookingService.updateBooking(bookingId, bookingCommand);
        UserInfo userInfo = userService.getUser(bookingInfo.getUserId());
        return BookingResponse.from(bookingInfo, userInfo);
    }

    public void deleteBooking(Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
