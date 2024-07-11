package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.application.dto.request.SeatRequest;
import com.concert_reservation.api.application.dto.response.SeatResponse;
import com.concert_reservation.api.business.model.dto.command.SeatCommand;
import com.concert_reservation.api.business.model.dto.info.ConcertInfo;
import com.concert_reservation.api.business.model.dto.info.SeatInfo;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.SeatRepository;
import com.concert_reservation.api.business.service.SeatService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
  private final SeatRepository seatRepository;

  @Override
  public List<SeatInfo> getAvailableSeats(SeatCommand seatCommand) {
    List<Seat> seats = seatRepository.findByConcertIdAndIsReservedFalse(seatCommand.getConcertId());
    return seats.stream()
        .map(seat -> SeatInfo.builder()
            .seatId(seat.getSeatId())
            .seatNumber(seat.getSeatNumber())
            .isReserved(seat.isReserved())
            .concertInfo(ConcertInfo.from(seat.getConcert())) // TODO ????
            .build())
        .collect(Collectors.toList());
  }


  @Override
  public SeatResponse createSeat(SeatRequest seatRequest) {
    return null;
  }

  @Override
  public SeatResponse getSeat(Long seatId) {
    return null;
  }

  @Override
  public SeatResponse updateSeat(Long seatId, SeatRequest seatRequest) {
    return null;
  }

  @Override
  public void deleteSeat(Long seatId) {

  }
}
