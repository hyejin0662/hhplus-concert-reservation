package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import java.util.List;

public interface SeatService {

	List<SeatInfo> getAvailableSeats(SeatCommand seatCommand);
	SeatInfo createSeat(SeatCommand seatCommand);
	SeatInfo getSeat(Long seatId);
	SeatInfo updateSeat(Long seatId, SeatCommand seatCommand);
	void deleteSeat(Long seatId);
}
