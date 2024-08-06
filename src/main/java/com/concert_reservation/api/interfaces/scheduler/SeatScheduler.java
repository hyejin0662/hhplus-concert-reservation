package com.concert_reservation.api.interfaces.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.concert.BookingFacade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeatScheduler {

	private final BookingFacade bookingFacade;


	@Scheduled(fixedRate = 2000)// 2초마다
	public void updateAvailableSeat()  {
		bookingFacade.updateAvailableSeat();
	}

}
