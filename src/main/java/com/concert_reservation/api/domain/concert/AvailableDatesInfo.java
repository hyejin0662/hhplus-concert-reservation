package com.concert_reservation.api.domain.concert;

import java.time.LocalDateTime;

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
public class AvailableDatesInfo {
	private Long concertOptionId;
	private Long concertId;
	private String singerName;
	private LocalDateTime concertDate;
	private Long capacity;
	private String location;

	public static AvailableDatesInfo from(ConcertOption concertOption) {
		return AvailableDatesInfo.builder()
			.concertOptionId(concertOption.getConcertOptionId())
			.concertId(concertOption.getConcertId())
			.singerName(concertOption.getSingerName())
			.concertDate(concertOption.getConcertDate())
			.capacity(concertOption.getCapacity())
			.location(concertOption.getLocation())
			.build();
	}
}