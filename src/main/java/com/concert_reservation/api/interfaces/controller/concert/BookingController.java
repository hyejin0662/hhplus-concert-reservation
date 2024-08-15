package com.concert_reservation.api.interfaces.controller.concert;

import static com.concert_reservation.common.model.WebResponseData.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.interfaces.controller.concert.dto.request.BookingRequest;
import com.concert_reservation.api.interfaces.controller.concert.dto.request.SeatRequest;
import com.concert_reservation.api.interfaces.controller.concert.dto.response.BookingResponse;
import com.concert_reservation.api.interfaces.controller.concert.dto.response.SeatResponse;
import com.concert_reservation.api.application.concert.BookingFacade;
import com.concert_reservation.common.model.WebResponseData;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping
    @Operation(summary = "예약 생성", description = "사용자가 예약을 생성합니다.")
    public WebResponseData<BookingResponse> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        return ok(bookingFacade.createBooking(bookingRequest));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "예약 조회", description = "사용자의 예약을 조회합니다.")
    public WebResponseData<BookingResponse> getBooking(@PathVariable String userId) {
        return ok(bookingFacade.getBooking(userId));
    }

    @DeleteMapping("/{bookingId}")
    @Operation(summary = "예약 삭제", description = "사용자의 예약을 삭제합니다.")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingFacade.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }


    // todo -> 예약 가능한 날짜
    // concert -> conert -> concertoption을 찾고, 해당하는 날짜에 예약을 조회해서, 예약이 안된 거 리턴



    @GetMapping("/available-dates")
    @Operation(summary = "예약 가능한 날짜 조회", description = "예약 가능한 날짜를 조회합니다.")
    public WebResponseData<List<BookingResponse.AvailableDates>> getAvailableDates(@ModelAttribute BookingRequest.AvailableDates seatRequest) {
        List<BookingResponse.AvailableDates> seatResponses = bookingFacade.getAvailableDates(seatRequest);
        return ok(seatResponses);
    }


    @GetMapping("/available-seats")
    @Operation(summary = "예약 가능한 좌석 조회", description = "예약 가능한 좌석을 조회합니다.")
    public WebResponseData<List<SeatResponse>> getAvailableSeats(@ModelAttribute SeatRequest seatRequest) {
        List<SeatResponse> seatResponses = bookingFacade.getAvailableSeats(seatRequest);
        return ok(seatResponses);
    }



}
